package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;

@Controller
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	@Value("${bank.home}")
    private String bank_home;
	
	@Value("${bank.acquirer}")
	private String bank_acquierer;
	
	private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody PaymentInquiryDTO piDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
//        za sada zakomentarisano
//        ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(bank_home + bank_acquierer, new HttpEntity<>(piDTO),
//                PaymentInquiryInfoDTO.class);
        
//        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        //privremeno
        PaymentInquiryInfoDTO piInfoDTO = new PaymentInquiryInfoDTO();
        piInfoDTO.setPaymentId(1);
        piInfoDTO.setPaymentUrl("some_url");
        return new ResponseEntity<>(piInfoDTO, HttpStatus.OK);
    }
}
