package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PricelistDTO extends BaseDTO{

	//@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateFrom;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateTo;

	private List<PricelistItemDTO> pricelistItems = new ArrayList<>();

}
