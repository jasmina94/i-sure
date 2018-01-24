package com.ftn.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PricelistItemDTO extends BaseDTO{

	@NotNull
	private double coefficient;

	@NotNull
	private double price;

	private RiskDTO risk;

}
