package com.ftn.model.dto;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricelistDTO {

	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateFrom;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateTo;

	private Collection<PricelistItemDTO> pricelistItems;
}
