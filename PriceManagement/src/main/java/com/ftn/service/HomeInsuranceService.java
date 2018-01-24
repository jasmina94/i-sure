package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.HomeInsuranceDTO;

public interface HomeInsuranceService {

	List<HomeInsuranceDTO> readAll();

	HomeInsuranceDTO create(HomeInsuranceDTO HomeInsuranceDTO);

	HomeInsuranceDTO update(Long id, HomeInsuranceDTO HomeInsuranceDTO);

	void delete(Long id);

	HomeInsuranceDTO findById(Long id);

	List<HomeInsuranceDTO> findByPersonalId(String personalId);
}
