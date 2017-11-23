package com.ftn.model.dto;

import com.ftn.model.PricelistItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PricelistItemDTO extends BaseDTO{
	private double coefficient;
	private double price;
	
	public PricelistItemDTO(PricelistItem pricelistItem) {
		this(pricelistItem, true);
	}
	
	public PricelistItemDTO(PricelistItem pricelistItem, boolean cascade) {
		super(pricelistItem);
		this.coefficient = pricelistItem.getCoefficient();
		this.price = pricelistItem.getPrice();
	}
	
	public PricelistItem construct() {
		final PricelistItem pricelistItem = new PricelistItem(this);
		pricelistItem.setCoefficient(coefficient);
		pricelistItem.setPrice(price);
		
		return pricelistItem;
	}
}
