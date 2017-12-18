package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentCheckoutDTO;
import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.model.dto.TransactionStatus;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.TransactionServiceImpl;

@Controller
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	@Value("${pc.home}")
    private String pc_home;
	
	@Value("${pc.payment.inquiries}")
	private String pc_inquiries;
	
	@Value("${merchant.id}")
    private String merchant_id;
	
	@Value("${merchant.password}")
	private String merchant_password;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private final TransactionService transactionService;

    @Autowired
    public PaymentInquiryController(TransactionServiceImpl transactionService){

        this.transactionService = transactionService;
    }
    
    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        
        transactionDTO = transactionService.findById(transactionDTO.getId());
        
        //payment part
        PaymentInquiryDTO piDTO = new PaymentInquiryDTO();
        piDTO.setMerchantId(merchant_id);
        piDTO.setMerchantPassword(merchant_password);
        piDTO.setAmount(transactionDTO.getAmount());
        //trebao bi biti Long za sada sam ga kastovao u int
        piDTO.setMerchantOrderId((int)transactionDTO.getId());
        piDTO.setMerchantTimestamp(transactionDTO.getTimestamp());
        //koji url da stavim?
        piDTO.setErrorUrl("tmp");
        
        System.out.println(pc_home + pc_inquiries);
        
        ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(pc_home + pc_inquiries, new HttpEntity<>(piDTO),
                PaymentInquiryInfoDTO.class);
        
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
