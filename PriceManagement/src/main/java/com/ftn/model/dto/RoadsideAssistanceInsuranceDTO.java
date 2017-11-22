package com.ftn.model.dto;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO {

	private Long id;
	
	private String ownerFirstName;
	private String ownerLastName;
	private String ucn;
	private String carBrand;
	// ENUMERACIJA?
	private String carType;
	private String yearOfManufacture;
	private String licencePlateNumber;
	private String undercarriageNumber;
	private double price;

	private Collection<InsurancePolicyDTO> policies;

}
