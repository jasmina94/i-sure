package com.ftn.service.implementation;

import com.ftn.model.database.Payment;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.PaymentRepository;
import com.ftn.service.OnlinePaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
public class OnlinePaymentServiceImpl implements OnlinePaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public List<Payment> read() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment create() {
        Payment payment = new Payment();
        String bankUrl = environmentProperties.getSelfUrl();
        String paymentUrl = bankUrl + "payment.html";
        payment.setUrl(paymentUrl);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Long id, Payment payment) {
        return null;
    }
}
