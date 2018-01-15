package com.ftn.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.Customer;
import com.ftn.model.Payment;
import com.ftn.model.TransactionStatus;
import com.ftn.model.PaymentType;
import com.ftn.model.Transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TransactionDTO extends BaseDTO{
	
	//@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timestamp;
	
	//@NotNull
	private TransactionStatus status;
	
	//@NotNull
	private PaymentTypeDTO paymentType;
	
	//@NotNull
	private Double amount;
	
	private PaymentDTO payment;
	
	@NotNull
	private InsurancePolicyDTO insurancePolicy;
	
	private Long acquiererOrderId;
	
	private Date acquiererTimestamp;
	
    public TransactionDTO(Transaction transaction){
        this(transaction, true);
    }
    
    public TransactionDTO(Transaction transaction, boolean cascade){
        super(transaction);
        this.timestamp = transaction.getTimestamp();
        this.status = transaction.getStatus();
        this.amount = transaction.getAmount();
        if(cascade) {
        	if(transaction.getPaymentType() != null) {
        		this.paymentType = new PaymentTypeDTO(transaction.getPaymentType());
        	}
        	if(transaction.getPayment() != null) {
        		this.payment = new PaymentDTO(transaction.getPayment());
        	}
        	if(transaction.getInsurancePolicy() != null) {
    			this.insurancePolicy = new InsurancePolicyDTO(transaction.getInsurancePolicy());
    		}
        }
    }



    public Transaction construct(){
        final Transaction transaction = new Transaction(this);
        transaction.setTimestamp(timestamp);
        transaction.setStatus(status);
        transaction.setAmount(amount);
        if(this.paymentType != null){
            transaction.setPaymentType(paymentType.construct());
        }
        if(this.payment != null){
            transaction.setPayment(payment.construct());
        }
        if(insurancePolicy != null) {
			transaction.setInsurancePolicy(insurancePolicy.construct());
		}
        return transaction;
    }
}
