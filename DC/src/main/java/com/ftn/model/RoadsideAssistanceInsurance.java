package com.ftn.model;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
	private String personalId;

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

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Risk> risks = new ArrayList<>();
	
	public RoadsideAssistanceInsurance(BaseDTO baseDTO){
		super(baseDTO);
	}

	public void merge(RoadsideAssistanceInsuranceDTO raiDTO){
		this.ownerFirstName = raiDTO.getOwnerFirstName();
		this.ownerLastName = raiDTO.getOwnerLastName();
		this.personalId = raiDTO.getPersonalId();
		this.carBrand = raiDTO.getCarBrand();
		this.carType = raiDTO.getCarType();
		this.yearOfManufacture = raiDTO.getYearOfManufacture();
		this.licencePlateNumber = raiDTO.getLicencePlateNumber();
		this.undercarriageNumber = raiDTO.getUndercarriageNumber();
		this.price = raiDTO.getPrice();
	}

	
}
