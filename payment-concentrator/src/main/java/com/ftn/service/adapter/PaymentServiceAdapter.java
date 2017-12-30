package com.ftn.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.service.AcquirerService;
import com.ftn.service.PayPalService;
import com.ftn.service.PaymentInquiryService;

@Component
public class PaymentServiceAdapter implements PaymentInquiryService{
	
	@Autowired
	private PayPalService payPalService;
	
	@Autowired
	private AcquirerService acquirerService;
	
	@Override
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO) {
		
		String paymentType = piDTO.getPaymentType();
		
		if(paymentType.equals("paypal")) {
			return payPalService.sendPaymentInquiry(piDTO);
		}else if(paymentType.equals("acquirer")) {
			return acquirerService.sendPaymentInquiry(piDTO);
		}else {
			return null;
		}
	}
	
}
