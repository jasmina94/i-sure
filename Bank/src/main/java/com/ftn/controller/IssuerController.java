package com.ftn.controller;

import com.ftn.exception.BadRequestException;
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
@CrossOrigin(origins = "*")
public class IssuerController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final IssuerService issuerService;

    private final RestTemplate restTemplate;


    @Autowired
    public IssuerController(IssuerService issuerService) {
        this.issuerService = issuerService;
        this.restTemplate = new RestTemplate();
    }


    @PostMapping
    public ResponseEntity receiveOrderFromPCC(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        PaymentResponseInfoDTO paymentResponseInfoDTO = issuerService.generateResponse(paymentOrderDTO);

        // Forward to PCC
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentResponseInfoDTO> entity = new HttpEntity<>(paymentResponseInfoDTO, headers);
        String responseUrl = environmentProperties.getPccUrl() + "/response";
        ResponseEntity<PaymentResponseInfoDTO> response = restTemplate.exchange(responseUrl, HttpMethod.POST, entity, PaymentResponseInfoDTO.class);

        return response;
    }
}
