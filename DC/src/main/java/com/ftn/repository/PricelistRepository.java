package com.ftn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Long>{
	Optional<Pricelist> findById(Long id);
}
