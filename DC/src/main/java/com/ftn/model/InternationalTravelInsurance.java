package com.ftn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_policy" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InternationalTravelInsurance extends Base{
	
	@Column
	private Date issueDate;
	@Column
	private int durationInDays;
	@Column
	private int numberOfPersons;
	@Column
	private double price;
	
	//private Collection<Risk> risks;
	
	public InternationalTravelInsurance(BaseDTO baseDTO){
		super(baseDTO);
	}
	
	public void merge(InternationalTravelInsuranceDTO itiDTO){
		this.issueDate = itiDTO.getIssueDate();
		this.durationInDays = itiDTO.getDurationInDays();
		this.numberOfPersons = itiDTO.getNumberOfPersons();
		this.price = itiDTO.getPrice();
		//fali mapiranje rizika
	}
}
