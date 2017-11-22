package com.ftn.model.dto;

import java.util.Collection;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricelistDTO {
	private Long id;
	private Date dateFrom;
	private Date dateTo;
	private Collection<PricelistItemDTO> pricelistItems;
}
