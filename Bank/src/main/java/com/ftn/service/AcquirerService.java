package com.ftn.service;

import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface AcquirerService {

    boolean checkInquiry(PaymentInquiryDTO paymentInquiryDTO);

    PaymentInquiryInfoDTO create();

    PaymentOrderDTO generateOrderTimestamp(PaymentOrderDTO paymentOrderDTO);
}
