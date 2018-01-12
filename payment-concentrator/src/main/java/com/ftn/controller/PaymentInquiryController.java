package com.ftn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.service.adapter.PaymentServiceAdapter;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	@Autowired
	private PaymentServiceAdapter paymentService;

    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody PaymentInquiryDTO piDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        
        return new ResponseEntity<>(paymentService.sendPaymentInquiry(piDTO, request), HttpStatus.OK);
    }
}
