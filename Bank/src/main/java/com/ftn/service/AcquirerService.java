package com.ftn.service;

import com.ftn.model.database.Account;
import com.ftn.model.dto.onlinepayment.*;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface AcquirerService {

    boolean checkInquiry(PaymentInquiryDTO paymentInquiryDTO);

    PaymentInquiryInfoDTO generateInquiryInfo(PaymentInquiryDTO paymentInquiryDTO);

    PaymentOrderDTO generateOrder(PaymentOrderDTO paymentOrderDTO, long paymentId);

    Account getMerchantAccount(PaymentInquiryDTO paymentInquiryDTO);

    PaymentCheckoutDTO generateCheckout(PaymentResponseInfoDTO paymentResponseInfoDTO);
}
