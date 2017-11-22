package com.ftn.service;

import com.ftn.model.dto.InsurancePolicyDTO;

public interface InsurancePolicyService {

	public InsurancePolicyDTO findAll();
	public InsurancePolicyDTO findById(Long id);
	public InsurancePolicyDTO create(InsurancePolicyDTO ipDto);
	public InsurancePolicyDTO update(InsurancePolicyDTO ipDto);
}
