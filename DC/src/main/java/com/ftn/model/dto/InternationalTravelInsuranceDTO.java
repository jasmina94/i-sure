package com.ftn.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.InternationalTravelInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InternationalTravelInsuranceDTO extends BaseDTO{

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	@NotNull
	private int durationInDays;
	@NotNull
	private int numberOfPersons;
	@NotNull
	private double price;
	
	//private Collection<RiskDTO> risks;

	public InternationalTravelInsuranceDTO(InternationalTravelInsurance iti){
		super(iti);
		this.issueDate = iti.getIssueDate();
		this.durationInDays = iti.getDurationInDays();
		this.numberOfPersons = iti.getNumberOfPersons();
		this.price = iti.getPrice();
		//fali mapiranje rizika
	}
	
	public InternationalTravelInsurance construct(){
		InternationalTravelInsurance iti = new InternationalTravelInsurance(this);
		iti.setIssueDate(this.issueDate);
		iti.setDurationInDays(this.durationInDays);
		iti.setNumberOfPersons(this.numberOfPersons);
		iti.setPrice(this.price);
		//fali mapiranje rizika
		return iti;
	}
}
