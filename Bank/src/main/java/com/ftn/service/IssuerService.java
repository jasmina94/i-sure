package com.ftn.service;

import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface IssuerService {

    PaymentResponseInfoDTO.CardAuthStatus checkCard(PaymentOrderDTO paymentOrderDTO);

    PaymentResponseInfoDTO.TransactionStatus checkTransaction(PaymentOrderDTO paymentOrderDTO);

    Transaction reserve(PaymentOrderDTO paymentOrderDTO);

    PaymentResponseInfoDTO makeResponse(PaymentOrderDTO paymentOrderDTO);
}
