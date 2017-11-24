package com.ftn.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Where(clause = SqlConstants.ACTIVE)
public class HomeInsurance extends Base {

	@Column(nullable = false)
	private String ownerFirstName;
	
	@Column(nullable = false)
	private String ownerLastName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String ucn;
	
	@Column(nullable = false)
	private double price;
	
	//private Collection<Risk> risks;
	
	public HomeInsurance(BaseDTO baseDTO){
		super(baseDTO);
		
	}
	
	public void merge(HomeInsuranceDTO homeInsuranceDTO){
		this.ownerFirstName = homeInsuranceDTO.getOwnerFirstName();
		this.ownerLastName = homeInsuranceDTO.getOwnerLastName();
		this.address = homeInsuranceDTO.getAddress();
		this.ucn = homeInsuranceDTO.getUcn();
		this.price = homeInsuranceDTO.getPrice();
		//fali mapiranje rizika
	}

}
