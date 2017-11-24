package com.ftn.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Where(clause = SqlConstants.ACTIVE)
public class RoadsideAssistanceInsurance extends Base{

	@Column(nullable = false)
	private String ownerFirstName;
	@Column(nullable = false)
	private String ownerLastName;
	@Column(nullable = false)
	private String ucn;
	@Column(nullable = false)
	private String carBrand;
	// ENUMERACIJA?
	@Column(nullable = false)
	private String carType;
	@Column(nullable = false)
	private String yearOfManufacture;
	@Column(nullable = false)
	private String licencePlateNumber;
	@Column(nullable = false)
	private String undercarriageNumber;
	@Column(nullable = false)
	private double price;
	
	//private List<Risk> risks = new ArrayList<>();
	
	public RoadsideAssistanceInsurance(BaseDTO baseDTO){
		super(baseDTO);
	}

	public void merge(RoadsideAssistanceInsuranceDTO raiDTO){
		this.ownerFirstName = raiDTO.getOwnerFirstName();
		this.ownerLastName = raiDTO.getOwnerLastName();
		this.ucn = raiDTO.getUcn();
		this.carBrand = raiDTO.getCarBrand();
		this.carType = raiDTO.getCarType();
		this.yearOfManufacture = raiDTO.getYearOfManufacture();
		this.licencePlateNumber = raiDTO.getLicencePlateNumber();
		this.undercarriageNumber = raiDTO.getUndercarriageNumber();
		this.price = raiDTO.getPrice();
		//fali mapiranje rizika
	}

	
}
