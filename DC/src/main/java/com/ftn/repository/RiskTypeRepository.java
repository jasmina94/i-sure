package com.ftn.repository;

import com.ftn.model.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface RiskTypeRepository extends JpaRepository<RiskType, Long> {

    Optional<RiskType> findById(Long id);

    Optional<RiskType> findByRiskTypeName(String name);
}
