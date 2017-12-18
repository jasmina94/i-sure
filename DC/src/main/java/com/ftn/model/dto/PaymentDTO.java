package com.ftn.model.dto;

import com.ftn.model.InsurancePolicy;
import com.ftn.model.Payment;
import com.ftn.model.Transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaymentDTO extends BaseDTO{
	private String paymentUrl;
	
	private InsurancePolicyDTO insurancePolicy;
	
	private TransactionDTO transaction;
	
	public PaymentDTO(Payment payment) {
		super(payment);
		this.paymentUrl = payment.getPaymentUrl();
		
		if(payment.getInsurancePolicy() != null) {
			this.insurancePolicy = new InsurancePolicyDTO(payment.getInsurancePolicy());
		}
	}
	
	public Payment construct() {
		final Payment payment = new Payment(this);
		payment.setPaymentUrl(paymentUrl);
		if(insurancePolicy != null) {
			payment.setInsurancePolicy(insurancePolicy.construct());
		}
		return payment;
	}
}
