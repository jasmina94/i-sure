package com.ftn.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TransactionDTO extends BaseDTO{
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timestamp;
	
	@NotNull
	private PaymentStatus status;
	
	@NotNull
	private PaymentTypeDTO paymentType;
	
	@NotNull
	private Double amount;
	
	private PaymentDTO payment;
	
	private Long acquiererOrderId;
	
	private Date acquiererTimestamp;	
}
