package com.ftn.repository;

import com.ftn.model.Risk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Optional<Risk> findById(Long id);

    Optional<Risk> findByRiskName(String name);
}
