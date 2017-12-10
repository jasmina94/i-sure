package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.onlinepayment.*;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.AcquirerService;
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

    @Autowired
    public AcquirerController(AcquirerService acquirerService){
        this.acquirerService = acquirerService;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    @GetMapping
    public ResponseEntity processInquiry(@Valid @RequestBody PaymentInquiryDTO paymentInquiryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        PaymentInquiryInfoDTO paymentInquiryInfoDTO = null;
        boolean validInquiry = acquirerService.checkInquiry(paymentInquiryDTO);
        if(validInquiry){
            paymentInquiryInfoDTO = acquirerService.create();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentInquiryInfoDTO> entity = new HttpEntity<>(paymentInquiryInfoDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(environmentProperties.getConcentratorUrl(), HttpMethod.POST, entity, String.class);

        return response;
    }

    @Transactional
    @PostMapping
    public ResponseEntity authenticate(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        paymentOrderDTO = acquirerService.generateOrderTimestamp(paymentOrderDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentOrderDTO> entity = new HttpEntity<PaymentOrderDTO>
                (paymentOrderDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(environmentProperties.getPccUrl(), HttpMethod.POST, entity, String.class);

        return response;
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

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentCheckoutDTO> entity = new HttpEntity<>(paymentCheckout, headers);

        ResponseEntity<String> response = restTemplate.exchange(environmentProperties.getConcentratorUrl(), HttpMethod.POST, entity, String.class);

        return response;
    }
}
