package com.ftn.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HomeInsuranceDTO extends BaseDTO{
	
	@NotNull
	private String ownerFirstName;
	@NotNull
	private String ownerLastName;
	@NotNull
	private String address;
	@NotNull
	private String ucn;
	@NotNull
	private double price;
	
	//private List<RiskDTO> risks;

}
