package com.ftn.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.paypal.config.PaypalPaymentIntent;
import com.ftn.paypal.config.PaypalPaymentMethod;
import com.ftn.paypal.util.URLUtils;
import com.ftn.service.PayPalService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalServiceImpl implements PayPalService{

	public static final String PAYPAL_SUCCESS_URL = "dummyinquiries/testni";
	public static final String PAYPAL_CANCEL_URL = "dummyinquiries/cancel";
	
	@Autowired
	private APIContext apiContext ;
	
	@Override
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO, HttpServletRequest request){
		
		
		
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" +PAYPAL_SUCCESS_URL;
		
		
		System.out.println("Success url " +successUrl);
		
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(String.format("%.2f", piDTO.getAmount()));

		Transaction transaction = new Transaction();
		transaction.setDescription("Paypal payment description");
		transaction.setAmount(amount);
		
		Payee payee = new Payee();
		payee.setEmail("teauvranju-facilitator@hotmail.com");
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod(PaypalPaymentMethod.paypal.toString());
		
		Payment payment = new Payment();
		payment.setIntent(PaypalPaymentIntent.sale.toString());
		payment.setPayer(payer);
		
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);
		
		Payment p;
		try {
			p = payment.create(apiContext);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
			return null;
		}
		
		List<Links> lista = p.getLinks();
		
		System.out.println("ID "+p.getId());
		
		String approval_url = null;
		
		for(Links links : lista){
			if(links.getRel().equals("approval_url")){
				approval_url = "redirect:" + links.getHref();
				break;
			}
		}
		
		//privremeno
		PaymentInquiryInfoDTO piInfoDTO = new PaymentInquiryInfoDTO();
	    piInfoDTO.setPaymentId(1);
	    piInfoDTO.setPaymentUrl(approval_url);
		
		return piInfoDTO;
	}



	@Override
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
