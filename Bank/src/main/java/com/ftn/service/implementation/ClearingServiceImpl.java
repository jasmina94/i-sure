package com.ftn.service.implementation;

import com.ftn.exception.ServiceFaultException;
import com.ftn.model.database.*;
import com.ftn.model.dto.error.ServiceFault;
import com.ftn.model.dto.mt102.GetMt102Request;
import com.ftn.model.dto.mt102.GetMt102Response;
import com.ftn.model.dto.mt102.Mt102;
import com.ftn.model.dto.mt102body.Mt102Body;
import com.ftn.model.dto.mt102header.Mt102Header;
import com.ftn.model.dto.mt900.Mt900;
import com.ftn.model.dto.mt910.Mt910;
import com.ftn.model.dto.types.TBankData;
import com.ftn.model.dto.types.TCorporateBody;
import com.ftn.model.dto.types.TCurrencyLabel;
import com.ftn.model.dto.types.TPaymentData;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.*;
import com.ftn.service.ClearingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by zlatan on 6/25/17.
 */
@Service
public class ClearingServiceImpl extends WebServiceGatewaySupport implements ClearingService {

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private Mt102Repository mt102Repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DailyAccountStateRepository dailyAccountStateRepository;

    @Autowired
    private AccountAnalyticsRepository accountAnalyticsRepository;

    @Autowired
    private SingleWarrantRepository singleWarrantRepository;

    @Override
    public Mt102 createMT102(Mt102Model mt102Model) {
        Mt102 mt102 = new Mt102();
        Mt102Header mt102Header = new Mt102Header();

        TBankData debtorBank = new TBankData();
        debtorBank.setSwiftCode(mt102Model.getSwiftDebtorBank());
        debtorBank.setAccountNumber(mt102Model.getAccountDebtorBank());

        TBankData creditorBank = new TBankData();
        creditorBank.setSwiftCode(mt102Model.getSwiftCreditorBank());
        creditorBank.setAccountNumber(mt102Model.getAccountCreditorBank());

        mt102Header.setDebtorBankData(debtorBank);
        mt102Header.setCreditorBankData(creditorBank);
        mt102Header.setTotalAmount(BigDecimal.valueOf(mt102Model.getTotalAmount()));
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(mt102Model.getCurrencyDate());
        try {
            mt102Header.setCurrencyDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        calendar.setTime(mt102Model.getWarrantDate());
        try {
            mt102Header.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        mt102Header.setCurrencyLabel(TCurrencyLabel.fromValue(mt102Model.getCurrency()));
        mt102Header.setMessageId(mt102Model.getMessageId());
        mt102.setMt102Header(mt102Header);

        //Pojedinacni nalozi
        for (SingleWarrant singleWarrant: mt102Model.getWarrants()){
            Mt102Body mt102Body = new Mt102Body();

            TCorporateBody debtor = new TCorporateBody();
            debtor.setName(singleWarrant.getDebtor());
            debtor.setAccountNumber(singleWarrant.getDebtorAccountNumber());
            mt102Body.setDebtorData(debtor);

            TCorporateBody creditor = new TCorporateBody();
            creditor.setName(singleWarrant.getCreditor());
            creditor.setAccountNumber(singleWarrant.getCreditorAccountNumber());
            mt102Body.setCreditorData(creditor);

            Mt102Body.Amount amount = new Mt102Body.Amount();
            amount.setValue(BigDecimal.valueOf(singleWarrant.getAmount()));
            amount.setCurrency(TCurrencyLabel.valueOf(singleWarrant.getCurrency()));
            mt102Body.setAmount(amount);

            TPaymentData chargeData = new TPaymentData();
            chargeData.setModel(BigInteger.valueOf(singleWarrant.getChargeModel()));
            chargeData.setReferenceNumber(singleWarrant.getChargeReferencingNumber());
            mt102Body.setChargeData(chargeData);

            TPaymentData incomeData = new TPaymentData();
            incomeData.setModel(BigInteger.valueOf(singleWarrant.getCreditorModel()));
            incomeData.setReferenceNumber(singleWarrant.getCreditorReferencingNumber());
            mt102Body.setIncomeData(incomeData);

            GregorianCalendar calendarNew = new GregorianCalendar();
            calendarNew.setTime(singleWarrant.getWarrantDate());
            try {
                mt102Body.setWarrantDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarNew));
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            mt102Body.setWarrantId(singleWarrant.getWarrantId());
            mt102Body.setPaymentPurpose(singleWarrant.getPaymentPurpose());

            mt102.getMt102Body().add(mt102Body);
        }
        return mt102;
    }

    @Override
    public void sendMT102(Mt102 mt102) {
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(GetMt102Request.class, GetMt102Response.class);
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        final GetMt102Request getMt102Request = new GetMt102Request();
        getMt102Request.setMt102(mt102);

        try{
            final GetMt102Response response = (GetMt102Response) getWebServiceTemplate()
                    .marshalSendAndReceive(environmentProperties.getNbsUrl(), getMt102Request);
        }catch (Exception e) {
            throw new ServiceFaultException("Wrong answer.", new ServiceFault("500", "Not possible sending mt102 to central bank!"));
        }
    }

    @Override
    public String processMT900(Mt900 mt900) {
        Optional<Mt102Model> mt102Model = mt102Repository.findByMessageId(mt900.getMessageId());
        if(!mt102Model.isPresent()){
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found mt102 model!"));
        }else{
            for (SingleWarrant singleWarrant: mt102Model.get().getWarrants()) {
                Optional<Account> debtorAccount = accountRepository.findByAccountNumber(singleWarrant.getDebtorAccountNumber());
                if(!debtorAccount.isPresent()){
                    throw new ServiceFaultException("Not found.", new ServiceFault("404", "Nije pronadjen racun duznika!"));
                }else{
                    Account debtorAccountReal = debtorAccount.get();
                    AccountAnalytics analytics = makeAnalytics(singleWarrant, debtorAccountReal, true, mt102Model.get().getCurrencyDate());
                    dailyStateChange(analytics, debtorAccountReal, true);
                    debtorAccountReal.setBalance(debtorAccountReal.getBalance() - debtorAccountReal.getReserved());
                    debtorAccountReal.setReserved(0.0);
                    accountRepository.save(debtorAccountReal);
                }
            }
        }
        return "ok";
    }

    @Override
    public String processMT910(Mt910 mt910) {
        Optional<Mt102Model> mt102Model = mt102Repository.findByMessageId(mt910.getMessageId());
        if(!mt102Model.isPresent()){
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found mt102 model in creditor's bank."));
        }else{
            for (SingleWarrant singleWarrant: mt102Model.get().getWarrants()) {
                Optional<Account> creditorAccount = accountRepository.findByAccountNumber(singleWarrant.getCreditorAccountNumber());
                if(!creditorAccount.isPresent()){
                    throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found creditor account number."));
                }else{
                    Account creditorAccountReal = creditorAccount.get();
                    AccountAnalytics analytics = makeAnalytics(singleWarrant, creditorAccountReal, false, mt102Model.get().getCurrencyDate());
                    dailyStateChange(analytics, creditorAccountReal, false);
                    creditorAccountReal.setBalance(creditorAccountReal.getBalance() + singleWarrant.getAmount().doubleValue());
                    accountRepository.save(creditorAccountReal);
                }
            }
        }
        return "ok";
    }

    @Override
    public void save(Mt102 mt102) {
        Mt102Model mt102Model = new Mt102Model();
        mt102Model.setSent(true);
        mt102Model.setWarrantDate(mt102.getMt102Header().getDate().toGregorianCalendar().getTime());
        mt102Model.setCurrencyDate(mt102.getMt102Header().getCurrencyDate().toGregorianCalendar().getTime());
        mt102Model.setMessageId(mt102.getMt102Header().getMessageId());
        mt102Model.setCurrency(mt102.getMt102Header().getCurrencyLabel().value());
        mt102Model.setTotalAmount(mt102.getMt102Header().getTotalAmount().doubleValue());
        mt102Model.setSwiftDebtorBank(mt102.getMt102Header().getDebtorBankData().getSwiftCode());
        mt102Model.setSwiftCreditorBank(mt102.getMt102Header().getCreditorBankData().getSwiftCode());
        mt102Model.setAccountDebtorBank(mt102.getMt102Header().getDebtorBankData().getAccountNumber());
        mt102Model.setAccountCreditorBank(mt102.getMt102Header().getCreditorBankData().getAccountNumber());
        mt102Model = mt102Repository.save(mt102Model);
        List<SingleWarrant> singleWarrants = new ArrayList<>();
        for (Mt102Body mt102Body : mt102.getMt102Body()){
            SingleWarrant singleWarrant = new SingleWarrant();
            singleWarrant.setWarrantId(mt102Body.getWarrantId());
            singleWarrant.setDebtor(mt102Body.getDebtorData().getName());
            singleWarrant.setCreditor(mt102Body.getCreditorData().getName());
            singleWarrant.setPaymentPurpose(mt102Body.getPaymentPurpose());
            singleWarrant.setWarrantDate(mt102Body.getWarrantDate().toGregorianCalendar().getTime());
            singleWarrant.setDebtorAccountNumber(mt102Body.getDebtorData().getAccountNumber());
            singleWarrant.setCreditorAccountNumber(mt102Body.getCreditorData().getAccountNumber());
            singleWarrant.setChargeModel(mt102Body.getChargeData().getModel().intValue());
            singleWarrant.setCreditorModel(mt102Body.getIncomeData().getModel().intValue());
            singleWarrant.setChargeReferencingNumber(mt102Body.getChargeData().getReferenceNumber());
            singleWarrant.setCreditorReferencingNumber(mt102Body.getIncomeData().getReferenceNumber());
            singleWarrant.setCurrency(mt102Body.getAmount().getCurrency().value());
            singleWarrant.setAmount(mt102Body.getAmount().getValue().doubleValue());
            singleWarrant.setMt102Model(mt102Model);
            singleWarrantRepository.save(singleWarrant);
            singleWarrants.add(singleWarrant);
        }
        mt102Model.setWarrants(singleWarrants);
        mt102Repository.save(mt102Model);
    }

    private AccountAnalytics makeAnalytics(SingleWarrant singleWarrant, Account debtorAccount, boolean debtor, Date currencyDate){
        AccountAnalytics analytics = new AccountAnalytics();
        analytics.setWarrantDate(singleWarrant.getWarrantDate());
        analytics.setCurrencyDate(currencyDate);
        analytics.setIncome(!debtor);
        analytics.setDebtor(singleWarrant.getDebtor());
        analytics.setCreditor(singleWarrant.getCreditor());
        analytics.setDebtorAccount(singleWarrant.getDebtorAccountNumber());
        analytics.setCreditorAccount(singleWarrant.getCreditorAccountNumber());
        analytics.setChargeModel(singleWarrant.getChargeModel());
        analytics.setApprovalModel(singleWarrant.getCreditorModel());
        analytics.setChargeReferencingNumber(singleWarrant.getChargeReferencingNumber());
        analytics.setApprovalReferencingNumber(singleWarrant.getCreditorReferencingNumber());
        analytics.setAmount(BigDecimal.valueOf(singleWarrant.getAmount()));
        analytics.setCurrency(singleWarrant.getCurrency());
        analytics.setPaymentPurpose(singleWarrant.getPaymentPurpose());
        return analytics;
    }

    private void dailyStateChange(AccountAnalytics accountAnalytics, Account account, boolean debtor){
        boolean dailyAccountStateFound = false;
        Date analyticsDate = accountAnalytics.getWarrantDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(analyticsDate);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        analyticsDate = calendar.getTime();
        for (DailyAccountState dailyAccountState : account.getDailyAccountStates()) {
            Date tempDatum = dailyAccountState.getDate();
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(tempDatum);
            tempCal.set(Calendar.MILLISECOND, 0);
            tempCal.set(Calendar.SECOND, 0);
            tempCal.set(Calendar.MINUTE, 0);
            tempCal.set(Calendar.HOUR, 0);
            tempDatum = tempCal.getTime();
            if (tempDatum.equals(analyticsDate)){
                if(debtor) {
                    dailyAccountState.setTotalCharge(dailyAccountState.getTotalCharge() + accountAnalytics.getAmount().doubleValue());
                    dailyAccountState.setNewState(dailyAccountState.getPreviousState() - dailyAccountState.getTotalCharge() + dailyAccountState.getTotalIncome());
                    dailyAccountState.setNumberOfChargeChanges(dailyAccountState.getNumberOfChargeChanges() + 1);
                }else{
                    dailyAccountState.setTotalIncome(dailyAccountState.getTotalIncome() + accountAnalytics.getAmount().doubleValue());
                    dailyAccountState.setNewState(dailyAccountState.getPreviousState() - dailyAccountState.getTotalCharge() + dailyAccountState.getTotalIncome());
                    dailyAccountState.setNumberOfIncomeChanges(dailyAccountState.getNumberOfIncomeChanges() + 1);
                }

                dailyAccountState.getAnalytics().add(accountAnalytics);
                accountAnalytics.setDailyAccountState(dailyAccountState);
                dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
                account.getDailyAccountStates().add(dailyAccountState);
                dailyAccountStateFound = true;
                break;
            }
        }
        if(!dailyAccountStateFound){
            DailyAccountState dailyAccountState = new DailyAccountState();
            dailyAccountState.setDate(analyticsDate);
            dailyAccountState.setAccount(account);
            dailyAccountState.setPreviousState(account.getBalance());
            if(debtor) {
                dailyAccountState.setTotalCharge(accountAnalytics.getAmount().doubleValue());
                dailyAccountState.setNewState(dailyAccountState.getPreviousState() - dailyAccountState.getTotalCharge() + dailyAccountState.getTotalIncome());
                dailyAccountState.setNumberOfChargeChanges(dailyAccountState.getNumberOfChargeChanges() + 1);
            }else{
                dailyAccountState.setTotalIncome(accountAnalytics.getAmount().doubleValue());
                dailyAccountState.setNewState(dailyAccountState.getPreviousState() - dailyAccountState.getTotalCharge() + dailyAccountState.getTotalIncome());
                dailyAccountState.setNumberOfIncomeChanges(dailyAccountState.getNumberOfIncomeChanges() + 1);
            }
            List<AccountAnalytics> analytics = new ArrayList<>();
            if(dailyAccountState.getAnalytics() == null){
                analytics.add(accountAnalytics);
                dailyAccountState.setAnalytics(analytics);
            }else{
                dailyAccountState.getAnalytics().add(accountAnalytics);
            }
            dailyAccountState = dailyAccountStateRepository.save(dailyAccountState);
            account.getDailyAccountStates().add(dailyAccountState);
            accountAnalytics.setDailyAccountState(dailyAccountState);
        }
        accountRepository.save(account);
        accountAnalyticsRepository.save(accountAnalytics);
    }
}
