package com.ftn.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;

@Service
public class PricelistServiceImplementation implements PricelistService{

	@Override
	public List<PricelistDTO> findAll() {
		System.out.println("find all");
		return null;
	}

	@Override
	public PricelistDTO findById(Long id) {
		System.out.println("find one");
		return null;
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {
		System.out.println("create");
		return null;
	}

	@Override
	public PricelistDTO update(PricelistDTO pricelistDTO) {
		System.out.println("update");
		return null;
	}

	@Override
	public void delete(Long id) {
		System.out.println("delete");
	}
}
