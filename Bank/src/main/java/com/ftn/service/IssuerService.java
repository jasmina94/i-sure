package com.ftn.service;

import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface IssuerService {

    boolean checkCustomer(String PAN);

    boolean checkCustomerAndAmount(PaymentOrderDTO paymentOrderDTO);

    PaymentResponseInfoDTO reserveAndResponse(PaymentOrderDTO paymentOrderDTO);
}
