package com.ftn.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;

public interface RoadsideAssistanceInsuranceService {

	List<RoadsideAssistanceInsuranceDTO> readAll();

    RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO);

    RoadsideAssistanceInsuranceDTO update(Long id, RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO);

    void delete(Long id);
    
    RoadsideAssistanceInsuranceDTO findById(Long id);
    
    public List<RoadsideAssistanceInsuranceDTO> findByUcn(String ucn);

	public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture);

	public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber);

	public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber);

	public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand);
	
	public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType);
}
