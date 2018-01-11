package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.PaymentInquiryService;

@Service
public class PaymentInquiryServiceImpl implements PaymentInquiryService{
	
	@Value("${merchant.id}")
    private String merchant_id;
	
	@Value("${merchant.password}")
	private String merchant_password;
	
	@Override
	public PaymentInquiryDTO create(TransactionDTO transactionDTO) {
        //payment part
        PaymentInquiryDTO piDTO = new PaymentInquiryDTO();
        piDTO.setMerchantId(merchant_id);
        piDTO.setMerchantPassword(merchant_password);
        piDTO.setPaymentType(transactionDTO.getPaymentType().getLabel());
        piDTO.setAmount(transactionDTO.getAmount());
        //trebao bi biti Long za sada sam ga kastovao u int
        piDTO.setMerchantOrderId((int)transactionDTO.getId());

        piDTO.setMerchantTimestamp(transactionDTO.getTimestamp());
        //koji url da stavim? - ovo je neka stranica koja treba da se napravi na portalu
        piDTO.setErrorUrl("tmp");

		return piDTO;
	}

}
