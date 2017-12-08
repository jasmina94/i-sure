package com.ftn.service.implementation;

import com.ftn.exception.ServiceFaultException;
import com.ftn.model.database.Account;
import com.ftn.model.database.AccountAnalytics;
import com.ftn.model.database.DailyAccountState;
import com.ftn.model.database.Mt103Model;
import com.ftn.model.dto.error.ServiceFault;
import com.ftn.model.dto.mt103.GetMt103Request;
import com.ftn.model.dto.mt103.GetMt103Response;
import com.ftn.model.dto.mt103.Mt103;
import com.ftn.model.dto.mt900.Mt900;
import com.ftn.model.dto.mt910.Mt910;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.*;
import com.ftn.service.RTGSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zlatan on 6/25/17.
 */
@Service
public class RTGSServiceImpl extends WebServiceGatewaySupport implements RTGSService {

    @Autowired
    private Mt103Repository mt103Repository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private AccountAnalyticsRepository accountAnalyticsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DailyAccountStateRepository dailyAccountStateRepository;

    @Override
    public void processMT103(Mt103 mt103) {
        Mt103Model mt103Model = new Mt103Model();
        mt103Model.setMessageId(mt103.getMessageId());
        mt103Model.setWarrantDate(mt103.getPaymentData().getWarrantDate().toGregorianCalendar().getTime());
        mt103Model.setCurrencyDate(mt103.getPaymentData().getCurrencyDate().toGregorianCalendar().getTime());
        mt103Model.setDebtor(mt103.getDebtorData().getName());
        mt103Model.setCreditor(mt103.getCreditorData().getName());
        mt103Model.setSwiftDebtorBank(mt103.getDebtorData().getBankData().getSwiftCode());
        mt103Model.setSwiftCreditorBank(mt103.getCreditorData().getBankData().getSwiftCode());
        mt103Model.setAccountDebtorBank(mt103.getDebtorData().getBankData().getAccountNumber());
        mt103Model.setAccountCreditorBank(mt103.getCreditorData().getBankData().getAccountNumber());
        mt103Model.setPaymentPurpose(mt103.getPaymentData().getPaymentPurpose());
        mt103Model.setDebtorAccountNumber(mt103.getDebtorData().getAccountNumber());
        mt103Model.setCreditorAccountNumber(mt103.getCreditorData().getAccountNumber());
        mt103Model.setChargeModel(mt103.getPaymentData().getChargeData().getModel());
        mt103Model.setCreditorModel(mt103.getPaymentData().getIncomeData().getModel());
        mt103Model.setChargeReferencingNumber(mt103.getPaymentData().getChargeData().getReferenceNumber());
        mt103Model.setCreditorReferencingNumber(mt103.getPaymentData().getIncomeData().getReferenceNumber());
        mt103Model.setCurrency(mt103.getPaymentData().getAmount().getCurrency());
        mt103Model.setAmount(mt103.getPaymentData().getAmount().getValue().doubleValue());

        mt103Repository.save(mt103Model);

        sendMT103(mt103);
    }

    @Override
    public void sendMT103(Mt103 mt103) {

        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(GetMt103Request.class, GetMt103Response.class);
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        final GetMt103Request getMt103Request = new GetMt103Request();
        getMt103Request.setMt103(mt103);

        try{
            final GetMt103Response response = (GetMt103Response) getWebServiceTemplate()
                    .marshalSendAndReceive(environmentProperties.getNbsUrl(), getMt103Request);
        }catch (RuntimeException e) {
            throw new ServiceFaultException("Wrong response.", new ServiceFault("500", "Impossible sending Mt103 model to central bank!"));
        }
    }

    @Override
    public String processMT900(Mt900 mt900) {
        Optional<Mt103Model> mt103Model = mt103Repository.findByMessageId(mt900.getMessageId());

        if(!mt103Model.isPresent()){
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found Mt103 model in debtor bank!"));
        }else{
            Optional <Account> debtorAccount = accountRepository.findByAccountNumber(mt103Model.get().getDebtorAccountNumber());
            if(!debtorAccount.isPresent()){
                throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found debtor account number!"));
            }else{
                Account debtorAccountReal = debtorAccount.get();
                debtorAccountReal.setBalance(debtorAccountReal.getBalance() - debtorAccount.get().getReserved());
                debtorAccount.get().setReserved(0.0);
                accountRepository.save(debtorAccountReal);
                makeAnalytics(mt103Model.get(), debtorAccountReal, true);
            }
        }
        return "ok";
    }

    @Override
    public String processMT910(Mt910 mt910) {
        Optional<Mt103Model> mt103Model = mt103Repository.findByMessageId(mt910.getMessageId());
        if(!mt103Model.isPresent()){
            return "Not found proper mt103 model!";
        }else{
            Optional <Account> creditorAccount = accountRepository.findByAccountNumber(mt103Model.get().getCreditorAccountNumber());
            if(!creditorAccount.isPresent()){
                return "Creditor accout doesn't exist!";
            }else{
                Account creditorAccountReal = creditorAccount.get();
                creditorAccountReal.setBalance(creditorAccountReal.getBalance() + mt103Model.get().getAmount());
                accountRepository.save(creditorAccountReal);
                makeAnalytics(mt103Model.get(), creditorAccountReal, false);
            }
        }
        return "ok";
    }

    @Override
    public void save(Mt103 mt103) {
        Mt103Model mt103Model = new Mt103Model();
        mt103Model.setMessageId(mt103.getMessageId());
        mt103Model.setWarrantDate(mt103.getPaymentData().getWarrantDate().toGregorianCalendar().getTime());
        mt103Model.setCurrencyDate(mt103.getPaymentData().getWarrantDate().toGregorianCalendar().getTime());
        mt103Model.setDebtor(mt103.getDebtorData().getName());
        mt103Model.setCreditor(mt103.getCreditorData().getName());
        mt103Model.setSwiftDebtorBank(mt103.getDebtorData().getBankData().getSwiftCode());
        mt103Model.setSwiftCreditorBank(mt103.getCreditorData().getBankData().getSwiftCode());
        mt103Model.setAccountDebtorBank(mt103.getDebtorData().getBankData().getAccountNumber());
        mt103Model.setAccountCreditorBank(mt103.getCreditorData().getBankData().getAccountNumber());
        mt103Model.setPaymentPurpose(mt103.getPaymentData().getPaymentPurpose());
        mt103Model.setDebtorAccountNumber(mt103.getDebtorData().getAccountNumber());
        mt103Model.setCreditorAccountNumber(mt103.getCreditorData().getAccountNumber());
        mt103Model.setChargeModel(mt103.getPaymentData().getChargeData().getModel());
        mt103Model.setCreditorModel(mt103.getPaymentData().getIncomeData().getModel());
        mt103Model.setChargeReferencingNumber(mt103.getPaymentData().getChargeData().getReferenceNumber());
        mt103Model.setCreditorReferencingNumber(mt103.getPaymentData().getIncomeData().getReferenceNumber());
        mt103Model.setCurrency(mt103.getPaymentData().getAmount().getCurrency());
        mt103Model.setAmount(mt103.getPaymentData().getAmount().getValue().doubleValue());
        mt103Repository.save(mt103Model);
    }

    private void makeAnalytics(Mt103Model mt103Model, Account debtorAccount, boolean debtor){
        AccountAnalytics analytics = new AccountAnalytics();
        analytics.setWarrantDate(mt103Model.getWarrantDate());
        analytics.setIncome(!debtor);
        analytics.setDebtor(mt103Model.getDebtor());
        analytics.setCreditor(mt103Model.getCreditor());
        analytics.setCurrencyDate(mt103Model.getCurrencyDate());
        analytics.setDebtorAccount(mt103Model.getDebtorAccountNumber());
        analytics.setChargeModel(mt103Model.getCreditorModel().longValue());
        analytics.setChargeReferencingNumber(mt103Model.getChargeReferencingNumber());
        analytics.setCreditorAccount(mt103Model.getCreditorAccountNumber());
        analytics.setApprovalModel(mt103Model.getCreditorModel().longValue());
        analytics.setApprovalReferencingNumber(mt103Model.getCreditorReferencingNumber());
        analytics.setAmount(BigDecimal.valueOf(mt103Model.getAmount()));
        analytics.setCurrency(mt103Model.getCurrency());
        analytics.setPaymentPurpose(mt103Model.getPaymentPurpose());
        changeDailyAccountState(analytics, mt103Model, debtorAccount, debtor);
    }

    private void changeDailyAccountState(AccountAnalytics analytics, Mt103Model mt103Model, Account account, boolean debtor){
        boolean dailyStateFound = false;
        Date anlyticsDate = analytics.getWarrantDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(anlyticsDate);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        anlyticsDate = calendar.getTime();

        for (DailyAccountState dailyAccountState : account.getDailyAccountStates()) {
            Date tempDatum = dailyAccountState.getDate();
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(tempDatum);
            tempCal.set(Calendar.MILLISECOND, 0);
            tempCal.set(Calendar.SECOND, 0);
            tempCal.set(Calendar.MINUTE, 0);
            tempCal.set(Calendar.HOUR, 0);
            tempDatum = tempCal.getTime();

            if (tempDatum.equals(anlyticsDate)){
                dailyAccountState.setPreviousState(dailyAccountState.getNewState());
                if(debtor) {
                    dailyAccountState.setTotalCharge(dailyAccountState.getTotalCharge() + mt103Model.getAmount());
                    dailyAccountState.setNewState(dailyAccountState.getNewState() - mt103Model.getAmount());
                }else{
                    dailyAccountState.setTotalIncome(dailyAccountState.getTotalIncome() + mt103Model.getAmount());
                    dailyAccountState.setNewState(dailyAccountState.getNewState() + mt103Model.getAmount());
                }

                dailyAccountState.getAnalytics().add(analytics);
                analytics.setDailyAccountState(dailyAccountState);
                dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
                account.getDailyAccountStates().add(dailyAccountState);
                dailyStateFound = true;
                break;
            }
        }

        if(!dailyStateFound){
            DailyAccountState dailyAccountState = new DailyAccountState();
            dailyAccountState.setDate(anlyticsDate);
            dailyAccountState.setAccount(account);

            if(debtor) {
                dailyAccountState.setTotalCharge(dailyAccountState.getTotalCharge() + mt103Model.getAmount());
                dailyAccountState.setPreviousState(account.getBalance() + mt103Model.getAmount());
                dailyAccountState.setNewState(account.getBalance());
                dailyAccountState.setNumberOfChargeChanges(1);
            }else{
                dailyAccountState.setTotalIncome(dailyAccountState.getTotalIncome() + mt103Model.getAmount());
                dailyAccountState.setPreviousState(account.getBalance() - mt103Model.getAmount());
                dailyAccountState.setNewState(account.getBalance());
                dailyAccountState.setNumberOfIncomeChanges(1);
            }
            List<AccountAnalytics> accountAnalytics = new ArrayList<>();
            if(dailyAccountState.getAnalytics() == null){
                 accountAnalytics.add(analytics);
                 dailyAccountState.setAnalytics(accountAnalytics);
            }else{
                dailyAccountState.getAnalytics().add(analytics);
            }

            dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
            account.getDailyAccountStates().add(dailyAccountState);
            analytics.setDailyAccountState(dailyAccountState);
        }

        accountRepository.save(account);
        accountAnalyticsRepository.save(analytics);
    }
}
