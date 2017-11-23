package com.ftn.model.dto;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.ftn.model.HomeInsurance;
import com.ftn.model.InsurancePolicy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HomeInsuranceDTO extends BaseDTO {

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

	//private List<RiskDTO> risks = new ArrayList<>();
	
	public HomeInsuranceDTO(HomeInsurance homeInsurance) {

		super(homeInsurance);
		this.ownerFirstName = homeInsurance.getOwnerFirstName();
		this.ownerLastName = homeInsurance.getOwnerLastName();
		this.address = homeInsurance.getAddress();
		this.ucn = homeInsurance.getUcn();
		this.price = homeInsurance.getPrice();
		//fali mapiranje rizika

	}
	
	public HomeInsurance construct(){
		HomeInsurance homeInsurance = new HomeInsurance(this);
		homeInsurance.setOwnerFirstName(this.ownerFirstName);
		homeInsurance.setOwnerLastName(this.ownerLastName);
		homeInsurance.setAddress(this.address);
		homeInsurance.setUcn(this.ucn);
		homeInsurance.setPrice(this.price);
		//fali mapiranje rizika
		return homeInsurance;
	}
	
}
