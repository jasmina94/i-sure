package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Merchant;
import com.ftn.model.database.Payment;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.MerchantRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.AcquirerService;
import com.ftn.service.OnlinePaymentService;
import com.ftn.service.PaymentService;
import com.ftn.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.Date;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class AcquirerServiceImpl implements AcquirerService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OnlinePaymentService onlinePaymentService;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public boolean checkInquiry(PaymentInquiryDTO paymentInquiryDTO) {
        boolean found = true;
        String merchantId = paymentInquiryDTO.getMerchantId();
        String merchantPassword = paymentInquiryDTO.getMerchantPassword();
        final Merchant merchant = merchantRepository.findByMerchantIdAndPassword
                (merchantId, merchantPassword).orElseThrow(NotFoundException::new);
        if(merchant == null){
            found = false;
        }
        return found;
    }

    @Override
    public PaymentInquiryInfoDTO create() {
        Payment payment = onlinePaymentService.create();
        PaymentInquiryInfoDTO paymentInquiryInfoDTO = new PaymentInquiryInfoDTO();
        paymentInquiryInfoDTO.setPaymentUrl(payment.getUrl());
        paymentInquiryInfoDTO.setPaymentId(payment.getId());
        return paymentInquiryInfoDTO;
    }

    @Override
    public PaymentOrderDTO generateOrder(PaymentOrderDTO paymentOrderDTO) {
        Transaction transaction = transactionService.create(paymentOrderDTO, Transaction.TransactionType.INCOME);
        paymentOrderDTO.setAcquirerOrderId(transaction.getId());
        paymentOrderDTO.setAcquirerTimestamp(transaction.getTimestamp());
        return paymentOrderDTO;
    }
}
