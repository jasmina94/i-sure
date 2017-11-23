package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.HomeInsurance;

@Repository
public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance, Long>{
	
	public Optional<HomeInsurance> findById(Long id);
	public List<HomeInsurance> findAll();
	public HomeInsurance save(HomeInsurance homeInsurance);
	public void delete(Long id);
	public Optional<HomeInsurance> findByUcn(String ucn);
}
