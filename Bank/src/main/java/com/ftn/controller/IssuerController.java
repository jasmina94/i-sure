package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.IssuerService;
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
@RequestMapping("/issuer")
public class IssuerController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final RestTemplate restTemplate;

    private final IssuerService issuerService;

    @Autowired
    public IssuerController(IssuerService issuerService){
        this.issuerService = issuerService;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    @PostMapping
    public ResponseEntity receiveOrderFromPCC(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        ResponseEntity<PaymentResponseInfoDTO> response;
        boolean valid = issuerService.checkCustomerAndAmount(paymentOrderDTO);

        if(valid){
            PaymentResponseInfoDTO paymentResponseInfoDTO = issuerService.reserveAndResponse(paymentOrderDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PaymentResponseInfoDTO> entity = new HttpEntity<>(paymentResponseInfoDTO, headers);
            String responseUrl = environmentProperties.getPccUrl() + "/response";
            response = restTemplate.exchange(responseUrl, HttpMethod.POST, entity, PaymentResponseInfoDTO.class);
        }else {
            throw new BadRequestException();
        }

        return response;
    }
}
