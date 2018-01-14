package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.service.AcquirerService;
import com.ftn.service.PaymentInquiryService;

@Service
public class AcquirerServiceImpl implements AcquirerService	{
	
	@Value("${bank.home}")
    private String bank_home;
	
	@Value("${bank.acquirer}")
	private String bank_acquierer;
	
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO) {
//      za sada zakomentarisano
//      ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(bank_home + bank_acquierer, new HttpEntity<>(piDTO),
//              PaymentInquiryInfoDTO.class);
      
//      return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
      //privremeno
      PaymentInquiryInfoDTO piInfoDTO = new PaymentInquiryInfoDTO();
      piInfoDTO.setPaymentId("1");
      piInfoDTO.setPaymentUrl("some_url");
      
      return piInfoDTO;
	}

}
