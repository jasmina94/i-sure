package com.ftn.service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;

public interface PayPalService {
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO);
}
