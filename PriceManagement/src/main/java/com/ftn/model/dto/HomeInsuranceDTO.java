package com.ftn.model.dto;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInsuranceDTO {
	
	private Long id;
	
	private String ownerFirstName;
	private String ownerLastName;
	private String address;
	private String ucn;
	private double price;
	
	private Collection<InsurancePolicyDTO> policies;

}
