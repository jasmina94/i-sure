package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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
	private String bank_acquirer;
	
	private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody PaymentInquiryDTO paymentInquiryDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        String url = bank_home + bank_acquirer + "/inquiry";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentInquiryDTO> entity = new HttpEntity<>(paymentInquiryDTO, headers);

        ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentInquiryInfoDTO.class);
        
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
