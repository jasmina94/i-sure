package com.ftn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "transaction" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Transaction extends Base{
	
	@Column(nullable = false)
	private Date timestamp;
	
	@Column(nullable = false)
	private PaymentStatus status;
	
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	
	@Column(nullable = false)
	private Double amount;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	private Long acquiererOrderId;
	
	private Date acquiererTimestamp;
	
	public Transaction(BaseDTO baseDTO) {
		super(baseDTO);
	}
	
	public void merge(TransactionDTO transactionDTO){
		this.timestamp = transactionDTO.getTimestamp();
		this.status = transactionDTO.getStatus();
		this.amount = transactionDTO.getAmount();
		if(transactionDTO.getPaymentType() != null){
			this.paymentType = transactionDTO.getPaymentType().construct();
		}
		if(transactionDTO.getPayment() != null){
			this.payment = transactionDTO.getPayment().construct();
		}
	}
}
