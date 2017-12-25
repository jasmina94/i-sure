package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Merchant;
import com.ftn.model.database.Payment;
import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.MerchantRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.service.OnlinePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Service
public class OnlinePaymentServiceImpl implements OnlinePaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public List<Payment> read() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findByPaymentId(long id) {
        Payment payment;
        try {
            payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);
        }catch (NotFoundException exception){
            payment = null;
        }
        return payment;
    }

    @Override
    public Payment create(PaymentInquiryDTO paymentInquiryDTO) {
        String merchantId = paymentInquiryDTO.getMerchantId();
        String merchantPassword = paymentInquiryDTO.getMerchantPassword();
        Merchant merchant = merchantRepository.findByMerchantIdAndPassword(merchantId, merchantPassword).orElseThrow(NotFoundException::new);
        long merchantOrderId = paymentInquiryDTO.getMerchantOrderId();
        Payment payment = new Payment();
        payment = paymentRepository.save(payment);
        long paymentId = payment.getId();
        String paymentUrl = environmentProperties.getSelfUrl();
        paymentUrl += "acquirer/order/" + paymentId;
        payment.setUrl(paymentUrl);
        payment.setMerchantOrderId(merchantOrderId);
        try {
            payment.setMerchant(merchant);
        }catch (NotFoundException exception){
            payment.setMerchant(null);
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Long id, Payment payment) {
        return null;
    }
}
