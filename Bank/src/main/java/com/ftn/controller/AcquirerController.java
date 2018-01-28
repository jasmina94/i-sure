package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.onlinepayment.*;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.AcquirerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
@CrossOrigin(origins = "*")
public class AcquirerController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final AcquirerService acquirerService;

    private final RestTemplate restTemplate;

    @Autowired
    public AcquirerController(AcquirerService acquirerService) {
        this.acquirerService = acquirerService;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    @PostMapping(value = "/inquiry")
    public ResponseEntity processInquiry(@Valid @RequestBody PaymentInquiryDTO paymentInquiryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        ResponseEntity<PaymentInquiryInfoDTO> response;
        boolean validInquiry = acquirerService.checkInquiry(paymentInquiryDTO);
        if (validInquiry) {
            PaymentInquiryInfoDTO paymentInquiryInfoDTO = acquirerService.generateInquiryInfo(paymentInquiryDTO);
            response = new ResponseEntity<>(paymentInquiryInfoDTO, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @Transactional
    @PostMapping(value = "/order/{paymentId}")
    public ResponseEntity processOrder(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO, @PathVariable long paymentId, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        // Start transaction
        paymentOrderDTO = acquirerService.generateOrder(paymentOrderDTO, paymentId);

        // Forward payment order to PCC and receive PaymentResponseInfo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentOrderDTO> entity = new HttpEntity<>(paymentOrderDTO, headers);

        ResponseEntity<PaymentResponseInfoDTO> response = restTemplate
                .exchange(environmentProperties.getPccUrl(), HttpMethod.POST, entity, PaymentResponseInfoDTO.class);

        PaymentResponseInfoDTO paymentResult = response.getBody();
        return new ResponseEntity<>(paymentResult, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/checkout")
    public PaymentCheckoutDTO processResponse(@Valid @RequestBody PaymentResponseInfoDTO paymentResponseInfoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();


        PaymentCheckoutDTO paymentCheckoutDTO = acquirerService.generateCheckout(paymentResponseInfoDTO);
        String concentratorUrl = environmentProperties.getConcentratorUrl() + "checkout";

        HttpEntity<PaymentCheckoutDTO> entity = new HttpEntity<>(paymentCheckoutDTO);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<PaymentCheckoutDTO> response = restTemplate.exchange(concentratorUrl, HttpMethod.POST, entity, PaymentCheckoutDTO.class);
        PaymentCheckoutDTO checkoutDTO = response.getBody();
        return checkoutDTO;
    }

    @Transactional
    @GetMapping(value = "/name")
    public ResponseEntity getBankName(){
        String bankName = acquirerService.getBankName();
        BankNameDTO bankNameDTO = new BankNameDTO();
        bankNameDTO.setName(bankName);
        return new ResponseEntity(bankNameDTO, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/amount/{paymentId}")
    public ResponseEntity getAmountForPayment(@PathVariable long paymentId){
        double amount = acquirerService.getAmountForPaymentId(paymentId);
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);
        return new ResponseEntity(amountDTO, HttpStatus.OK);
    }
}
