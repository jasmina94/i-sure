package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.ftn.model.User;
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

	private List<RiskDTO> risks = new ArrayList<>();

	@Override
	public String toString() {
		return "InternationalTravelInsurance: { issueDate=\"" + issueDate + "\"" + ", durationInDays=" + durationInDays + ", numberOfPersons=" + numberOfPersons + ", price=" + price + " }";
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.issueDate);
		hash = 59 * hash + Objects.hashCode(this.durationInDays);
		hash = 59 * hash + Objects.hashCode(this.numberOfPersons);
		hash = 59 * hash + Objects.hashCode(this.price);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final InternationalTravelInsuranceDTO other = (InternationalTravelInsuranceDTO) obj;
		if (!Objects.equals(this.issueDate, other.issueDate)) {
			return false;
		}
		if (!Objects.equals(this.durationInDays, other.durationInDays)) {
			return false;
		}
		if (!Objects.equals(this.numberOfPersons, other.numberOfPersons)) {
			return false;
		}
		if (!Objects.equals(this.price, other.price)) {
			return false;
		}
		if (!Objects.equals(this.risks, other.risks)) {
			return false;
		}
		return true;
	}

}
