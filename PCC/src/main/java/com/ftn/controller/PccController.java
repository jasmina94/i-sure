package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentOrderDTO;
import com.ftn.model.dto.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Jasmina on 10/12/2017.
 */
@RestController
@RequestMapping("/pcc")
public class PccController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final RestTemplate restTemplate;

    private final BankService bankService;

    public PccController(BankService bankService) {
        this.restTemplate = new RestTemplate();
        this.bankService = bankService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity receiveOrder(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO,
                                       BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        bankService.setAcquirerUrl(request);
        String BIN = paymentOrderDTO.getPAN().substring(1, 6);
        String issuerUrl = bankService.getIssuerUrl(BIN);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentOrderDTO> entity = new HttpEntity<>(paymentOrderDTO, headers);

        // Pass to issuer PaymentOrder and get back PaymentResponse
        ResponseEntity<PaymentResponseInfoDTO> response = restTemplate
                .exchange(issuerUrl, HttpMethod.POST, entity, PaymentResponseInfoDTO.class);

        // Respond to acquirer
        return new ResponseEntity<>(paymentOrderDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/response")
    public ResponseEntity receiveResponse(@Valid @RequestBody PaymentResponseInfoDTO paymentResponseInfoDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        // Forward response to acquirer
        String acquirerUrl =  environmentProperties.getAcquirerUrl() + "/acquirer/checkout";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentResponseInfoDTO> entity = new HttpEntity<>(paymentResponseInfoDTO, headers);

        ResponseEntity<PaymentResponseInfoDTO> response = restTemplate
                .exchange(acquirerUrl, HttpMethod.POST, entity, PaymentResponseInfoDTO.class);

        // Respond to issuer
        return new ResponseEntity(paymentResponseInfoDTO, HttpStatus.OK);
    }
}
