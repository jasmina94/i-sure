package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.Customer;
import com.ftn.model.HomeInsurance;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.InternationalTravelInsurance;
import com.ftn.model.RoadsideAssistanceInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsurancePolicyDTO extends BaseDTO {

	@NotNull
	private double totalValue;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfIssue;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateBecomeEffective;

	private List<CustomerDTO> customers = new ArrayList<>();

	@NotNull
	private InternationalTravelInsuranceDTO internationalTravelInsuranceDTO;

	private HomeInsuranceDTO homeInsurance;
	private RoadsideAssistanceInsuranceDTO roadsideAssistanceInsurance;

	// private List<RiskDTO> risks = new ArrayList<>();

	public InsurancePolicyDTO(InsurancePolicy insurancePolicy) {
		this(insurancePolicy, true);
	}

	public InsurancePolicyDTO(InsurancePolicy insurancePolicy, boolean casscade) {
		super(insurancePolicy);
		this.totalValue = insurancePolicy.getTotalValue();
		this.dateOfIssue = insurancePolicy.getDateOfIssue();
		this.dateBecomeEffective = insurancePolicy.getDateBecomeEffective();

		if (casscade) {

			HomeInsuranceDTO hiDTO;
			if (insurancePolicy.getHomeInsurance() != null) {
				hiDTO = new HomeInsuranceDTO(insurancePolicy.getHomeInsurance());
				this.homeInsurance = hiDTO;
			}

			if (insurancePolicy.getRoadsideAssistanceInsurance() != null) {
				RoadsideAssistanceInsuranceDTO raiDTO = new RoadsideAssistanceInsuranceDTO(
						insurancePolicy.getRoadsideAssistanceInsurance());
				this.roadsideAssistanceInsurance = raiDTO;
			}
			InternationalTravelInsuranceDTO itiDTO = new InternationalTravelInsuranceDTO(insurancePolicy.getInternationalTravelInsurance());

			this.internationalTravelInsuranceDTO = itiDTO;
			
			
			// this.customers =
			// insurancePolicy.getCustomers().stream().map(CustomerDTO::new).collect(Collectors.toList());
			this.customers.clear();
			for (Customer customer : insurancePolicy.getCustomers()) {
				customers.add(new CustomerDTO(customer));
			}
		}
	}

	public InsurancePolicy construct() {
		final InsurancePolicy insurancePolicy = new InsurancePolicy(this);
		insurancePolicy.setTotalValue(totalValue);
		insurancePolicy.setDateOfIssue(dateOfIssue);
		insurancePolicy.setDateBecomeEffective(dateBecomeEffective);

		InternationalTravelInsurance itiIntern = this.internationalTravelInsuranceDTO.construct();
		if (this.homeInsurance != null) {
			HomeInsurance hiIntern = this.homeInsurance.construct();
			insurancePolicy.setHomeInsurance(hiIntern);
		}

		if (this.roadsideAssistanceInsurance != null) {
			RoadsideAssistanceInsurance raiIntern = this.roadsideAssistanceInsurance.construct();
			insurancePolicy.setRoadsideAssistanceInsurance(raiIntern);
		}

		insurancePolicy.setInternationalTravelInsurance(itiIntern);

		// insurancePolicy.setCustomers(this.customers.stream().map(Customer::new).collect(Collectors.toList()));
		insurancePolicy.getCustomers().clear();
		for (CustomerDTO customerDTO : customers) {
			insurancePolicy.getCustomers().add(customerDTO.construct());
		}
		/*
		 * fali preslikavanje za Ucesnike, Osiguranje kuce, Pomoc na putu i
		 * glavno osiguranje i za rizike
		 */
		return insurancePolicy;
	}

}
