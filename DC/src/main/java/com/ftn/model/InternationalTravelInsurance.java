package com.ftn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Risk> risks = new ArrayList<>();
	
	public InternationalTravelInsurance(BaseDTO baseDTO){
		super(baseDTO);
	}
	
	public void merge(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO){
		this.issueDate = internationalTravelInsuranceDTO.getIssueDate();
		this.durationInDays = internationalTravelInsuranceDTO.getDurationInDays();
		this.numberOfPersons = internationalTravelInsuranceDTO.getNumberOfPersons();
		this.price = internationalTravelInsuranceDTO.getPrice();
	}
}
