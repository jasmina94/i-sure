package com.ftn.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.InternationalTravelInsurance;

public interface InternationalTravelInsuranceRepository extends JpaRepository<InternationalTravelInsurance, Long> {

	public Optional<InternationalTravelInsurance> findById(Long id);

	public List<InternationalTravelInsurance> findAll();

	public InternationalTravelInsurance save(InternationalTravelInsurance internationalTravelInsurance);

	public void delete(Long id);

	public List<InternationalTravelInsurance> findByIssueDate(Date issueOfDate);

	public List<InternationalTravelInsurance> findByDurationInDays(int durationInDays);

	public List<InternationalTravelInsurance> findByNumberOfPersons(int numberOfPerson);

}
