package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ftn.model.RoadsideAssistanceInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO extends BaseDTO{

	@NotNull
	@Size(max = 80)
	private String ownerFirstName;
	@NotNull
	@Size(max = 80)
	private String ownerLastName;
	@NotNull
	@Size(min = 13, max = 13)
    @Pattern(regexp = "[0-9]*")
	private String ucn;
	@NotNull
	@Size(max = 80)
	private String carBrand;
	// ENUMERACIJA?
	@NotNull
	@Size(max = 80)
	private String carType;
	@NotNull
	@Size(min = 4, max = 4)
	@Pattern(regexp = "[0-9]*")
	private String yearOfManufacture;
	@NotNull
	@Size(min = 7, max = 7)
	@Pattern(regexp = "\\w*")
	private String licencePlateNumber;
	@NotNull
	@Size(min = 7, max = 7)
	@Pattern(regexp = "\\w*")
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
