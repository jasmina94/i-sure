package com.ftn.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	//private ArrayList<RiskDTO> risks;

}
