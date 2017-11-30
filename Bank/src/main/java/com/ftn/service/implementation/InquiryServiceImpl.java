package com.ftn.service.implementation;

import com.ftn.exception.ServiceFaultException;
import com.ftn.model.database.Account;
import com.ftn.model.database.AccountAnalytics;
import com.ftn.model.database.DailyAccountState;
import com.ftn.model.dto.error.ServiceFault;
import com.ftn.model.dto.section.Section;
import com.ftn.model.dto.sectionheader.SectionHeader;
import com.ftn.model.dto.sectionitem.SectionItem;
import com.ftn.model.dto.statementaccountinquiry.StatementAccountInquiry;
import com.ftn.model.dto.types.TChanges;
import com.ftn.model.dto.types.TCorporateBody;
import com.ftn.model.dto.types.TPaymentData;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.AccountAnalyticsRepository;
import com.ftn.repository.AccountRepository;
import com.ftn.repository.DailyAccountStateRepository;
import com.ftn.service.InquiryService;
import com.ftn.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by zlatan on 26/06/2017.
 */
@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DailyAccountStateRepository dailyAccountStateRepository;

    @Autowired
    private AccountAnalyticsRepository accountAnalyticsRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private SectionService sectionService;

    @Override
    public void process(StatementAccountInquiry statementAccountInquiry) {
        int desiredSection = statementAccountInquiry.getSectionSerialNumber().intValue();
        int totalSectionNumber = 0;
        String accountNumber = statementAccountInquiry.getAccountNumber();
        Date inquiryDate = statementAccountInquiry.getDate().toGregorianCalendar().getTime();
        if (checkAccount(accountNumber)) {
            Account account = accountRepository.findByAccountNumber(accountNumber).get();
            Optional<DailyAccountState> dailyAccountState = dailyAccountStateRepository.findByAccountAndDate(account, inquiryDate);
            if (dailyAccountState.isPresent()) {
                DailyAccountState dailyAccountStateReal = dailyAccountState.get();
                List<AccountAnalytics> analytics = accountAnalyticsRepository.findByDailyAccountState(dailyAccountStateReal);
                int analyticsNumber = analytics.size();
                if (analyticsNumber % 3 != 0) {
                    totalSectionNumber = Math.round(analyticsNumber / 3 + 1);
                } else {
                    totalSectionNumber = analyticsNumber / 3;
                }
                if (totalSectionNumber < desiredSection) {
                    throw new ServiceFaultException("Not found.", new ServiceFault("404", "Trazeni redni broj presek ne postoji!"));
                }
                if (analytics.size() == 0) {
                    throw new ServiceFaultException("Not found.", new ServiceFault("404", "Nije pronadjena nijedna transakcija za prosledjeni datum!"));
                } else {
                    if (analyticsNumber > 3) {
                        int start = (desiredSection - 1) * 3;
                        int end = start + 3;
                        if (end > (analytics.size() - 1)) {
                            end = analytics.size();
                        }
                        List<AccountAnalytics> helperList = analytics.subList(start, end);
                        Section section = makeSection(dailyAccountStateReal, helperList, totalSectionNumber);
                        sectionService.send(section);
                    } else {
                        Section section = makeSection(dailyAccountStateReal, analytics, totalSectionNumber);
                        sectionService.send(section);
                    }
                }
            } else {
                throw new ServiceFaultException("Not found.", new ServiceFault("404", "Nije pronadjeno dnevno stanje za racun u banci!"));
            }
        } else {
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Racun zahteva nije pronadjen u banci!"));
        }
    }

    private Section makeSection(DailyAccountState dailyAccountState, List<AccountAnalytics> accountAnalytics, int numberOfSections) {
        Section section = new Section();
        SectionHeader sectionHeader = new SectionHeader();
        List<SectionItem> sectionItems = new ArrayList<>();

        sectionHeader.setSectionNumber(BigInteger.valueOf(numberOfSections));
        sectionHeader.setAccountNumber(dailyAccountState.getAccount().getAccountNumber());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(dailyAccountState.getDate());
        try {
            sectionHeader.setDateOfWarrant(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        sectionHeader.setPreviousState(BigDecimal.valueOf(dailyAccountState.getPreviousState()));
        sectionHeader.setNewState(BigDecimal.valueOf(dailyAccountState.getNewState()));

        TChanges chargeChanges = new TChanges();
        chargeChanges.setChangesNumber(BigInteger.valueOf(dailyAccountState.getNumberOfChargeChanges()));
        chargeChanges.setTotal(BigDecimal.valueOf(dailyAccountState.getTotalCharge()));

        TChanges incomeChanges = new TChanges();
        incomeChanges.setChangesNumber(BigInteger.valueOf(dailyAccountState.getNumberOfIncomeChanges()));
        incomeChanges.setTotal(BigDecimal.valueOf(dailyAccountState.getTotalIncome()));

        sectionHeader.setCharge(chargeChanges);
        sectionHeader.setIncome(incomeChanges);

        for (AccountAnalytics analytics : accountAnalytics) {
            SectionItem sectionItem = new SectionItem();

            TCorporateBody debtor = new TCorporateBody();
            debtor.setAccountNumber(analytics.getDebtorAccount());
            debtor.setName(analytics.getDebtor());
            sectionItem.setDebtorData(debtor);

            TCorporateBody creditor = new TCorporateBody();
            creditor.setAccountNumber(analytics.getCreditorAccount());
            creditor.setName(analytics.getCreditor());
            sectionItem.setCreditorData(creditor);

            SectionItem.PaymentData paymentData = new SectionItem.PaymentData();
            paymentData.setPaymentPurpose(analytics.getPaymentPurpose());
            paymentData.setAmount(analytics.getAmount());
            paymentData.setDirection((analytics.isIncome()) ? "I" : "C");

            GregorianCalendar gregorWarrant = new GregorianCalendar();
            GregorianCalendar gregorCurrency = new GregorianCalendar();
            gregorWarrant.setTime(analytics.getWarrantDate());
            gregorCurrency.setTime(analytics.getCurrencyDate());
            try {
                paymentData.setWarrantDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorWarrant));
                paymentData.setCurrencyDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorCurrency));
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            TPaymentData charge = new TPaymentData();
            charge.setModel(BigInteger.valueOf(analytics.getChargeModel()));
            charge.setReferenceNumber(analytics.getChargeReferencingNumber());
            paymentData.setChargeData(charge);

            TPaymentData income = new TPaymentData();
            income.setModel(BigInteger.valueOf(analytics.getApprovalModel()));
            income.setReferenceNumber(analytics.getApprovalReferencingNumber());
            paymentData.setIncomeData(income);

            sectionItem.setPaymentData(paymentData);
            sectionItems.add(sectionItem);
        }

        section.setSectionHeader(sectionHeader);
        section.setSectionItem(sectionItems);

        return section;
    }

    private boolean checkAccount(String accountNumber) {
        Optional<Account> racun = accountRepository.findByAccountNumber(accountNumber);
        if (racun.isPresent()) {
            String swiftCode = racun.get().getBank().getSWIFTcode();
            if (swiftCode.equals(environmentProperties.getSwiftCode())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
