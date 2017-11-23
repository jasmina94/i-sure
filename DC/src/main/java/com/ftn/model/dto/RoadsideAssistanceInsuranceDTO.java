package com.ftn.model.dto;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.ftn.model.RoadsideAssistanceInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO extends BaseDTO{

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
	
	public RoadsideAssistanceInsuranceDTO(RoadsideAssistanceInsurance rai){
		super(rai);
		this.ownerFirstName = rai.getOwnerFirstName();
		this.ownerLastName = rai.getOwnerLastName();
		this.ucn = rai.getUcn();
		this.carBrand = rai.getCarBrand();
		this.carType = rai.getCarType();
		this.yearOfManufacture = rai.getYearOfManufacture();
		this.licencePlateNumber = rai.getLicencePlateNumber();
		this.undercarriageNumber = rai.getUndercarriageNumber();
		this.price = rai.getPrice();
		//fali mapiranje rizika
				
	}

	public RoadsideAssistanceInsurance construct(){
		RoadsideAssistanceInsurance rai = new RoadsideAssistanceInsurance();
		 rai.setOwnerFirstName(this.ownerFirstName);
		 rai.setOwnerLastName(this.ownerLastName);
		 rai.setUcn(this.ucn);
		 rai.setCarBrand(this.carBrand);
		 rai.setCarType(this.carType);
		 rai.setYearOfManufacture(this.yearOfManufacture);
		 rai.setLicencePlateNumber(this.licencePlateNumber);
		 rai.setUndercarriageNumber(this.undercarriageNumber);
		 rai.setPrice(this.price);
		 //fali mapiranje rizika
		 return rai;
	}
	
}
