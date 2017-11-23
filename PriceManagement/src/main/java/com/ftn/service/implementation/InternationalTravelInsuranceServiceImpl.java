package com.ftn.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.service.InternationalTravelInsuranceService;

@Service
public class InternationalTravelInsuranceServiceImpl implements InternationalTravelInsuranceService{

	@Override
	public List<InternationalTravelInsuranceDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternationalTravelInsuranceDTO update(Long id,
			InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InternationalTravelInsuranceDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByIssueDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
