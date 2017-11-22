package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsurancePolicyDTO {

	private Long id;
	
	private double totalValue;
	private Date dateOfIssue;
	private Date dateBecomeEffective;
	private double totalPrice;

	// private List<Insured> participants = new ArrayList<>();

	private InternationalTravelInsuranceDTO iti;
	private HomeInsuranceDTO homeInsurance;
	private RoadsideAssistanceInsuranceDTO roadsideAssistanceInsurance;

}
