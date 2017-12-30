package com.ftn.service.implementation;

import org.springframework.stereotype.Service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.service.PayPalService;

@Service
public class PayPalServiceImpl implements PayPalService{

	@Override
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO) {
		return null;
	}
}
