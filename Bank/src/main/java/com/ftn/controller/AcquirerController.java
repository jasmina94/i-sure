package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.database.Payment;
import com.ftn.model.dto.onlinepayment.*;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.AcquirerService;
import com.ftn.service.OnlinePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * Created by Jasmina on 04/12/2017.
 */
@RestController
@RequestMapping("/acquirer")
public class AcquirerController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final RestTemplate restTemplate;

    private final AcquirerService acquirerService;

    private final OnlinePaymentService onlinePaymentService;

    @Autowired
    public AcquirerController(AcquirerService acquirerService, OnlinePaymentService onlinePaymentService){
        this.restTemplate = new RestTemplate();
        this.acquirerService = acquirerService;
        this.onlinePaymentService = onlinePaymentService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity processInquiry(@Valid @RequestBody PaymentInquiryDTO paymentInquiryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        ResponseEntity<String> response;
        boolean validInquiry = acquirerService.checkInquiry(paymentInquiryDTO);
        if(validInquiry){
            PaymentInquiryInfoDTO paymentInquiryInfoDTO = acquirerService.create();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PaymentInquiryInfoDTO> entity = new HttpEntity<>(paymentInquiryInfoDTO, headers);
            response = restTemplate.exchange(environmentProperties.getConcentratorUrl(), HttpMethod.POST, entity, String.class);
        }else {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @Transactional
    @PostMapping
    public ResponseEntity sendOrderToPCC(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        // Start transaction
        paymentOrderDTO = acquirerService.generateOrder(paymentOrderDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentOrderDTO> entity = new HttpEntity<>(paymentOrderDTO, headers);

        ResponseEntity<PaymentOrderDTO> response = restTemplate
                .exchange(environmentProperties.getPccUrl(), HttpMethod.POST, entity, PaymentOrderDTO.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }


    @Transactional
    @PostMapping(value = "/checkout")
    public ResponseEntity receiveAndSend(@Valid @RequestBody PaymentResponseInfoDTO paymentResponseInfoDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        PaymentCheckoutDTO paymentCheckout = new PaymentCheckoutDTO();
        paymentCheckout.setAcquirerOrderId(paymentResponseInfoDTO.getAcquirerOrderId());
        paymentCheckout.setAcquirerTimestamp(paymentResponseInfoDTO.getAcquirerTimestamp());
        paymentCheckout.setMerchantOrderId(1); //change get it from DB
        paymentCheckout.setPaymentId(1); // change get it from DB

//        Forward to concentrator
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<PaymentCheckoutDTO> entity = new HttpEntity<>(paymentCheckout, headers);
//        ResponseEntity<String> response = restTemplate.exchange(environmentProperties.getConcentratorUrl(),
//                  HttpMethod.POST, entity, String.class);

        return new ResponseEntity<>(paymentResponseInfoDTO,HttpStatus.OK);
    }
}
