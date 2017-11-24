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
public class InsurancePolicyDTO extends BaseDTO{

	@NotNull
	private double totalValue;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfIssue;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateBecomeEffective;
	
	// private List<Insured> participants = new ArrayList<>();

	@NotNull
	private InternationalTravelInsuranceDTO iti;
	
	private HomeInsuranceDTO homeInsurance;
	private RoadsideAssistanceInsuranceDTO roadsideAssistanceInsurance;

}
