package com.ftn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ftn.model.dto.PaymentInquiryInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.service.PayPalService;
import com.ftn.service.adapter.PaymentServiceAdapter;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	public static final String PAYPAL_SUCCESS_URL = "/testni";
	public static final String PAYPAL_CANCEL_URL = "/cancel";
	
	@Autowired
	PayPalService paypalService;
	
	@Autowired
	private PaymentServiceAdapter paymentService;

    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody PaymentInquiryDTO piDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
		PaymentInquiryInfoDTO paymentInquiryInfoDTO = paymentService.sendPaymentInquiry(piDTO, request);
        return new ResponseEntity<>(paymentInquiryInfoDTO, HttpStatus.OK);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(@RequestParam("token") String token){
    	paypalService.cancelPayment(token);
		return "cancel";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId,@RequestParam("token") String token, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				paypalService.successPayment(token);
				return "success";
			}else{
				paypalService.cancelPayment(token);
				return "cancel";
			}
		} catch (PayPalRESTException e) {
			paypalService.cancelPayment(token);
			e.printStackTrace();
		}
		return "cancel";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "tt")
	public String tt(){
		return "success";
	}
}
