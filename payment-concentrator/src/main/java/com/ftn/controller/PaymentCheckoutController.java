package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentCheckoutDTO;

@Controller
@RequestMapping("/checkout")
public class PaymentCheckoutController {
	
	@Value("${pc.home}")
    private String pc_home;
	
	@Value("${pc.payment.checkout}")
	private String pc_payment_checkout;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping
    public ResponseEntity recievePaymentCheckout(@Valid @RequestBody PaymentCheckoutDTO paymentCheckoutDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
            throw new BadRequestException();
		
		ResponseEntity<String> response = restTemplate.postForEntity(pc_home + pc_payment_checkout,
				new HttpEntity<>(paymentCheckoutDTO),
				String.class);
		
		//ne znam sta string treba da vrati
    	return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
