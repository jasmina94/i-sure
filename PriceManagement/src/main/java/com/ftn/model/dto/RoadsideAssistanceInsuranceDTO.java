package com.ftn.model.dto;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO  extends BaseDTO{

	@NotNull
	private String ownerFirstName;
	@NotNull
	private String ownerLastName;
	@NotNull
	private String ucn;
	@NotNull
	private String carBrand;
	// ENUMERACIJA?
	@NotNull
	private String carType;
	@NotNull
	private String yearOfManufacture;
	@NotNull
	private String licencePlateNumber;
	@NotNull
	private String undercarriageNumber;
	@NotNull
	private double price;

	//private List<RiskDTO> risks = new ArrayList<>();

}
