package com.ftn.model.dto;

import java.util.Collection;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternationalTravelInsuranceDTO {

	private Long id;
	
	private Date issueDate;
	private int durationInDays;
	private int numberOfPersons;
	private double price;

	private Collection<InsurancePolicyDTO> policies;

}
