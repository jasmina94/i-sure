package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.RoadsideAssistanceInsurance;

public interface RoadsideAssistanceInsuranceRepository extends JpaRepository<RoadsideAssistanceInsurance, Long>{
	
	public Optional<RoadsideAssistanceInsurance> findById(Long id);

	public List<RoadsideAssistanceInsurance> findAll();

	public RoadsideAssistanceInsurance save(RoadsideAssistanceInsurance roadsideAssistanceInsurance);

	public void delete(Long id);

	public List<RoadsideAssistanceInsurance> findByUcn(String ucn);

	public List<RoadsideAssistanceInsurance> findByYearOfManufacture(String yearOfManifacture);

	public List<RoadsideAssistanceInsurance> findByLicencePlateNumber(String licencePlateNumber);

	public List<RoadsideAssistanceInsurance> findByUndercarriageNumber(String undercarriageNumber);

	public List<RoadsideAssistanceInsurance> findByCarBrand(String carBrand);
	
	public List<RoadsideAssistanceInsurance> findByCarType(String carType);

}
