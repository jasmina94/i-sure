package com.ftn.service.implementation;

import com.ftn.exception.ServiceFaultException;
import com.ftn.model.database.*;
import com.ftn.model.dto.error.ServiceFault;
import com.ftn.model.dto.mt102.Mt102;
import com.ftn.model.dto.mt103.Mt103;
import com.ftn.model.dto.types.TBankData;
import com.ftn.model.dto.types.TPaymentData;
import com.ftn.model.dto.types.TTransferParticipiant;
import com.ftn.model.dto.warrant.Warrant;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.*;
import com.ftn.service.ClearingService;
import com.ftn.service.PaymentService;
import com.ftn.service.RTGSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.*;

/**
 * Created by zlatan on 6/24/17.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountAnalyticsRepository accountAnalyticsRepository;

    @Autowired
    private DailyAccountStateRepository dailyAccountStateRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private Mt102Repository mt102Repository;

    @Autowired
    private SingleWarrantRepository singleWarrantRepository;

    @Autowired
    private RTGSService RTGSService;

    @Autowired
    private ClearingService clearingService;

    @Autowired
    private EnvironmentProperties environmentProperties;



    @Override
    public void process(Warrant warrant) {
        boolean debtorInner = checkCompany(warrant.getTransferData().getDebtor().getParticipiantAccount());
        boolean creditorInner = checkCompany(warrant.getTransferData().getCreditor().getParticipiantAccount());
        if(!debtorInner) {
            throw new ServiceFaultException("Not found", new ServiceFault("404", "Debtor account not found!"));
        }else if(debtorInner && creditorInner) {
            innerTransfer(warrant);
        } else if(debtorInner && !creditorInner) {
            medjubankarskiPromet(warrant);
        }
    }

    private boolean checkCompany(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent()){
            String swiftCode = account.get().getBank().getSWIFTcode();
            if(swiftCode.equals(environmentProperties.getSwiftCode())){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    private void innerTransfer(Warrant warrant) {
        TTransferParticipiant debtor = warrant.getTransferData().getDebtor();
        TTransferParticipiant creditor = warrant.getTransferData().getCreditor();

        Optional<Account> debtorAccount = accountRepository.findByAccountNumber(debtor.getParticipiantAccount());
        Optional<Account> creditorAccount = accountRepository.findByAccountNumber(creditor.getParticipiantAccount());
        if(debtorAccount.isPresent() && creditorAccount.isPresent()){
            debtorAccount.get().setBalance(debtorAccount.get().getBalance() - warrant.getTransferData().getAmount().doubleValue());
            creditorAccount.get().setBalance(creditorAccount.get().getBalance() + warrant.getTransferData().getAmount().doubleValue());
            accountRepository.save(debtorAccount.get());
            accountRepository.save(creditorAccount.get());
            makeAnalytics(warrant, debtorAccount.get(), creditorAccount.get());
        }else {
            throw new ServiceFaultException("Not found", new ServiceFault("404", "Debtor or creditor account not found!"));
        }

    }

    private void medjubankarskiPromet(Warrant warrant) {
        TTransferParticipiant debtor = warrant.getTransferData().getDebtor();
        TTransferParticipiant creditor = warrant.getTransferData().getCreditor();

        Account debtorAccount = accountRepository.findByAccountNumber(debtor.getParticipiantAccount()).get();
        Account creditorAccount = accountRepository.findByAccountNumber(creditor.getParticipiantAccount()).get();

        if(warrant.isUrgent() || warrant.getTransferData().getAmount().doubleValue() >= 250000.00){
            // RTGS
            debtorAccount.setReserved(warrant.getTransferData().getAmount().doubleValue());
            accountRepository.save(debtorAccount);
            Mt103 mt103 = createMt103(warrant, debtorAccount, creditorAccount);
            RTGSService.processMT103(mt103);
        }else{
            // Clearing i settlement
            String swiftDebtorBank = environmentProperties.getSwiftCode();
            String swiftCreditorBank = "";
            Optional<Bank> creditorBank = bankRepository.findById(creditorAccount.getBank().getId());
            Bank creditorBankReal = null;
            if(!creditorBank.isPresent()){
                throw new ServiceFaultException("Not found.", new ServiceFault("404", "Creditor bank not found!"));
            }else{
                creditorBankReal = creditorBank.get();
                swiftCreditorBank = creditorBankReal.getSWIFTcode();
            }
            //Rezervisi sredstva
            debtorAccount.setReserved(debtorAccount.getReserved() + warrant.getTransferData().getAmount().doubleValue());
            accountRepository.save(debtorAccount);

            List<Mt102Model> mt102Model = mt102Repository.findBySwiftDebtorBankAndSwiftCreditorBankAndSent(swiftDebtorBank, swiftCreditorBank, false);
            if(mt102Model.isEmpty()){
                makeNewMT102Model(warrant, debtorAccount, creditorAccount);
            }else{
                SingleWarrant singleWarrant = makeSingleWarrant(warrant);
                singleWarrant.setMt102Model(mt102Model.get(0));
                mt102Model.get(0).getWarrants().add(singleWarrant);
                mt102Model.get(0).setTotalAmount(mt102Model.get(0).getTotalAmount() + singleWarrant.getAmount());
                singleWarrantRepository.save(singleWarrant);
                mt102Repository.save(mt102Model.get(0));
                if(mt102Model.get(0).getWarrants().size() >= 2){
                    Mt102 mt102 = clearingService.createMT102(mt102Model.get(0));
                    mt102Model.get(0).setSent(true);
                    mt102Repository.save(mt102Model.get(0));
                    clearingService.sendMT102(mt102);
                }
            }
       }
    }

    private void makeNewMT102Model(Warrant warrant, Account debtorAccount, Account creditorAccount) {
        Mt102Model mt102Model = new Mt102Model();
        Optional<Bank> debtorBank = bankRepository.findById(debtorAccount.getBank().getId());
        Optional<Bank> creditorBank = bankRepository.findById(creditorAccount.getBank().getId());
        if(!creditorBank.isPresent() || !debtorBank.isPresent()){
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Creditor or debtor bank is not found!"));
        }

        mt102Model.setMessageId(UUID.randomUUID().toString());
        mt102Model.setSwiftDebtorBank(environmentProperties.getSwiftCode());
        mt102Model.setSwiftCreditorBank(creditorBank.get().getSWIFTcode());
        mt102Model.setAccountDebtorBank(debtorBank.get().getAccountNumber());
        mt102Model.setAccountCreditorBank(creditorBank.get().getAccountNumber());
        mt102Model.setTotalAmount(mt102Model.getTotalAmount() + warrant.getTransferData().getAmount().doubleValue());
        mt102Model.setCurrency(warrant.getTransferData().getCurrencyLabel().value());
        mt102Model.setCurrencyDate(warrant.getCurrencyDate().toGregorianCalendar().getTime());
        mt102Model.setWarrantDate(warrant.getWarrantDate().toGregorianCalendar().getTime());
        mt102Model.setSent(false);

        List<SingleWarrant> singleWarrants = new ArrayList<>();
        mt102Model.setWarrants(singleWarrants);

        SingleWarrant singleWarrant = makeSingleWarrant(warrant);
        mt102Model.getWarrants().add(singleWarrant);
        mt102Model = mt102Repository.save(mt102Model);
        singleWarrant.setMt102Model(mt102Model);
        singleWarrantRepository.save(singleWarrant);
    }

    private SingleWarrant makeSingleWarrant(Warrant warrant) {
        SingleWarrant singleWarrant = new SingleWarrant();
        singleWarrant.setWarrantId(warrant.getMessageId());
        singleWarrant.setDebtor(warrant.getDebtor());
        singleWarrant.setCreditor(warrant.getCreditor());
        singleWarrant.setPaymentPurpose(warrant.getPaymentPurpose());
        singleWarrant.setWarrantDate(warrant.getWarrantDate().toGregorianCalendar().getTime());
        singleWarrant.setDebtorAccountNumber(warrant.getTransferData().getDebtor().getParticipiantAccount());
        singleWarrant.setCreditorAccountNumber(warrant.getTransferData().getCreditor().getParticipiantAccount());
        singleWarrant.setChargeModel(((int) warrant.getTransferData().getDebtor().getTransferModel()));
        singleWarrant.setCreditorModel((int) warrant.getTransferData().getCreditor().getTransferModel());
        singleWarrant.setChargeReferencingNumber(warrant.getTransferData().getDebtor().getReferenceNumber());
        singleWarrant.setCreditorReferencingNumber(warrant.getTransferData().getCreditor().getReferenceNumber());
        singleWarrant.setAmount(warrant.getTransferData().getAmount().doubleValue());
        singleWarrant.setCurrency(warrant.getTransferData().getCurrencyLabel().value());
        return singleWarrant;
    }

    private Mt103 createMt103(Warrant warrant, Account debtorAccount, Account creditorAccount) {
        Mt103 mt103 = new Mt103();
        mt103.setMessageId(UUID.randomUUID().toString());

        Mt103.DebtorData debtorData = new Mt103.DebtorData();
        debtorData.setName(warrant.getDebtor());
        debtorData.setAccountNumber(warrant.getTransferData().getDebtor().getParticipiantAccount());

        Mt103.CreditorData creditorData = new Mt103.CreditorData();
        creditorData.setName(warrant.getCreditor());
        creditorData.setAccountNumber(warrant.getTransferData().getCreditor().getParticipiantAccount());

        Optional<Bank> debtorBank = bankRepository.findById(debtorAccount.getBank().getId());
        Optional<Bank> creditorBank = bankRepository.findById(creditorAccount.getBank().getId());

        if(!debtorBank.isPresent() || !creditorBank.isPresent())
            throw new ServiceFaultException("Not found!", new ServiceFault("404", "Creditor or debtor bank is not found!"));


        TBankData debtorBankData = new TBankData();
        debtorBankData.setAccountNumber(debtorBank.get().getAccountNumber());
        debtorBankData.setSwiftCode(debtorBank.get().getSWIFTcode());

        debtorData.setBankData(debtorBankData);
        mt103.setDebtorData(debtorData);

        TBankData creditorBankData = new TBankData();
        creditorBankData.setAccountNumber(creditorBank.get().getAccountNumber());
        creditorBankData.setSwiftCode(creditorBank.get().getSWIFTcode());

        creditorData.setBankData(creditorBankData);
        mt103.setCreditorData(creditorData);

        Mt103.PaymentData paymentData = new Mt103.PaymentData();
        paymentData.setWarrantDate(warrant.getWarrantDate());
        paymentData.setCurrencyDate(warrant.getCurrencyDate());

        Mt103.PaymentData.Amount amount = new Mt103.PaymentData.Amount();
        amount.setValue(warrant.getTransferData().getAmount());
        amount.setCurrency(warrant.getTransferData().getCurrencyLabel().value());
        paymentData.setAmount(amount);

        TPaymentData chargeData = new TPaymentData();
        TPaymentData incomeData = new TPaymentData();

        chargeData.setModel(BigInteger.valueOf(warrant.getTransferData().getDebtor().getTransferModel()));
        chargeData.setReferenceNumber(warrant.getTransferData().getDebtor().getReferenceNumber());

        incomeData.setModel(BigInteger.valueOf(warrant.getTransferData().getCreditor().getTransferModel()));
        incomeData.setReferenceNumber(warrant.getTransferData().getCreditor().getReferenceNumber());

        paymentData.setIncomeData(incomeData);
        paymentData.setChargeData(chargeData);
        paymentData.setPaymentPurpose(warrant.getPaymentPurpose());

        mt103.setPaymentData(paymentData);

        return mt103;
    }

    private void makeAnalytics(Warrant warrant, Account debtorAccount, Account creditorAccount){
        AccountAnalytics debtorAnalytics = new AccountAnalytics();
        AccountAnalytics creditorAnalytics = new AccountAnalytics();

        debtorAnalytics.setWarrantDate(warrant.getWarrantDate().toGregorianCalendar().getTime());
        debtorAnalytics.setIncome(false);
        debtorAnalytics.setDebtor(warrant.getDebtor());
        debtorAnalytics.setCreditor(warrant.getCreditor());
        debtorAnalytics.setCurrencyDate(warrant.getCurrencyDate().toGregorianCalendar().getTime());
        debtorAnalytics.setDebtorAccount(warrant.getTransferData().getDebtor().getParticipiantAccount());
        debtorAnalytics.setChargeModel(warrant.getTransferData().getDebtor().getTransferModel());
        debtorAnalytics.setChargeReferencingNumber(warrant.getTransferData().getDebtor().getReferenceNumber());
        debtorAnalytics.setCreditorAccount(warrant.getTransferData().getCreditor().getParticipiantAccount());
        debtorAnalytics.setApprovalModel(warrant.getTransferData().getCreditor().getTransferModel());
        debtorAnalytics.setApprovalReferencingNumber(warrant.getTransferData().getCreditor().getReferenceNumber());
        debtorAnalytics.setAmount(warrant.getTransferData().getAmount());
        debtorAnalytics.setCurrency(warrant.getTransferData().getCurrencyLabel().value());
        debtorAnalytics.setPaymentPurpose(warrant.getPaymentPurpose());

        accountAnalyticsRepository.save(debtorAnalytics);

        creditorAnalytics.setWarrantDate(warrant.getWarrantDate().toGregorianCalendar().getTime());
        creditorAnalytics.setIncome(true);
        creditorAnalytics.setDebtor(warrant.getDebtor());
        creditorAnalytics.setCreditor(warrant.getCreditor());
        creditorAnalytics.setCurrencyDate(warrant.getCurrencyDate().toGregorianCalendar().getTime());
        creditorAnalytics.setDebtorAccount(warrant.getTransferData().getDebtor().getParticipiantAccount());
        creditorAnalytics.setChargeModel(warrant.getTransferData().getDebtor().getTransferModel());
        creditorAnalytics.setChargeReferencingNumber(warrant.getTransferData().getDebtor().getReferenceNumber());
        creditorAnalytics.setCreditorAccount(warrant.getTransferData().getCreditor().getParticipiantAccount());
        creditorAnalytics.setApprovalModel(warrant.getTransferData().getCreditor().getTransferModel());
        creditorAnalytics.setApprovalReferencingNumber(warrant.getTransferData().getCreditor().getReferenceNumber());
        creditorAnalytics.setAmount(warrant.getTransferData().getAmount());
        creditorAnalytics.setCurrency(warrant.getTransferData().getCurrencyLabel().value());
        creditorAnalytics.setPaymentPurpose(warrant.getPaymentPurpose());

        accountAnalyticsRepository.save(creditorAnalytics);

        dailyStateChange(debtorAnalytics, warrant, debtorAccount, true);
        dailyStateChange(creditorAnalytics, warrant, creditorAccount, false);
    }

    private void dailyStateChange(AccountAnalytics accountAnalytics, Warrant warrant, Account account, boolean isDebtor){
        boolean dailyStateFound = false;
        Date analyticDate = accountAnalytics.getWarrantDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(analyticDate);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        analyticDate = calendar.getTime();

        for (DailyAccountState dailyAccountState : account.getDailyAccountStates()) {
            Date tempDatum = dailyAccountState.getDate();
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(tempDatum);
            tempCal.set(Calendar.MILLISECOND, 0);
            tempCal.set(Calendar.SECOND, 0);
            tempCal.set(Calendar.MINUTE, 0);
            tempCal.set(Calendar.HOUR, 0);
            tempDatum = tempCal.getTime();

            if (tempDatum.equals(analyticDate)){
                dailyAccountState.setPreviousState(dailyAccountState.getNewState());
                if(isDebtor) {
                    dailyAccountState.setPreviousState(dailyAccountState.getNewState());
                    dailyAccountState.setNewState(dailyAccountState.getNewState() - warrant.getTransferData().getAmount().doubleValue());
                }else{
                    dailyAccountState.setPreviousState(dailyAccountState.getNewState());
                    dailyAccountState.setNewState(dailyAccountState.getNewState() + warrant.getTransferData().getAmount().doubleValue());
                }

                dailyAccountState.getAnalytics().add(accountAnalytics);
                accountAnalytics.setDailyAccountState(dailyAccountState);
                dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
                account.getDailyAccountStates().add(dailyAccountState);
                dailyStateFound = true;
                break;
            }
        }

        if(!dailyStateFound){
            DailyAccountState dailyAccountState = new DailyAccountState();
            dailyAccountState.setDate(analyticDate);
            dailyAccountState.setAccount(account);

            if(isDebtor) {
                dailyAccountState.setPreviousState(account.getBalance() + warrant.getTransferData().getAmount().doubleValue());
                dailyAccountState.setNewState(account.getBalance());
                dailyAccountState.setNumberOfChargeChanges(1);
            }else{
                dailyAccountState.setPreviousState(account.getBalance() - warrant.getTransferData().getAmount().doubleValue());
                dailyAccountState.setNewState(account.getBalance());
                dailyAccountState.setNumberOfIncomeChanges(1);
            }

            dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
            account.getDailyAccountStates().add(dailyAccountState);
            accountAnalytics.setDailyAccountState(dailyAccountState);
        }

        accountRepository.save(account);
        accountAnalyticsRepository.save(accountAnalytics);
    }
}
