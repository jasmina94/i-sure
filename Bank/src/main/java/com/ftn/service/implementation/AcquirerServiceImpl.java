package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Merchant;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.repository.MerchantRepository;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.AcquirerService;
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
    private TransactionRepository transactionRepository;

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
        PaymentInquiryInfoDTO paymentInquiryInfo = new PaymentInquiryInfoDTO();
        paymentInquiryInfo.setPaymentUrl("https://localhost:8091/payment/"); //Change redirect on form page
        paymentInquiryInfo.setPaymentId(1);
        return paymentInquiryInfo;
    }

    @Override
    public PaymentOrderDTO generateOrderTimestamp(PaymentOrderDTO paymentOrderDTO) {
        paymentOrderDTO.setAcquirerOrderId(111); // Get id from transaction
        paymentOrderDTO.setAcquirerTimestamp(new Date());
        return paymentOrderDTO;
    }
}
