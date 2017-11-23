package com.ftn.model.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricelistItemDTO {
	private Long id;
	private double coefficient;
	private double price;
}
