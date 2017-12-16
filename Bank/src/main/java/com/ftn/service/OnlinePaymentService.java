package com.ftn.service;

import com.ftn.model.database.Payment;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
public interface OnlinePaymentService {

    List<Payment> read();

    Payment create();

    Payment update(Long id, Payment payment);
}
