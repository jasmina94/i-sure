package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ftn.model.dto.TransactionDTO;
import com.ftn.model.dto.TransactionStatus;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.TransactionServiceImpl;

@Controller
@RequestMapping("/checkout")
public class PaymentCheckoutController {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private final TransactionService transactionService;

    @Autowired
    public PaymentCheckoutController(TransactionServiceImpl transactionService){

        this.transactionService = transactionService;
    }
	
	@PostMapping
    public ResponseEntity recievePaymentCheckout(@Valid @RequestBody PaymentCheckoutDTO paymentCheckoutDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
            throw new BadRequestException();
		
    	TransactionDTO transaction = transactionService.findById(paymentCheckoutDTO.getMerchantOrderId());
    	
    	if(!paymentCheckoutDTO.getSuccessUrl().equals(null)) {
    		transaction.setStatus(TransactionStatus.BOOKED);
    	}else if(!paymentCheckoutDTO.getSuccessUrl().equals(null)) {
    		transaction.setStatus(TransactionStatus.REVERSED);
    	}
    	
    	transaction.setAcquiererOrderId(paymentCheckoutDTO.getAcquirerOrderId());
    	transaction.setAcquiererTimestamp(paymentCheckoutDTO.getAcquirerTimestamp());
    	//ovde treba postaviti payment, treba se dogovoriti ocemo li imati citav payment ili samo id
    	//transaction.setPaymentId(paymentCheckoutDTO.getPaymentId());
    	
    	transaction = transactionService.update(transaction.getId(), transaction);
    	
    	//ovde isto ne znam sta treba da vratim
    	return new ResponseEntity<>("Ne znam sta da vratim", HttpStatus.OK);
    }
}
