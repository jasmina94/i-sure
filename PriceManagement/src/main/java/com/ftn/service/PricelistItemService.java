package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.PricelistItemDTO;

public interface PricelistItemService {
	List<PricelistItemDTO> findAll();
	PricelistItemDTO findById(Long id);
	PricelistItemDTO create(PricelistItemDTO pricelistItemDTO);
	PricelistItemDTO update(PricelistItemDTO pricelistItemDTO);
	void delete(Long id);
}
