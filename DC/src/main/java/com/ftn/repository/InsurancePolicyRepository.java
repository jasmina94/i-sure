package com.ftn.repository;

import com.ftn.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

    Optional<InsurancePolicy> findById(Long id);

    Optional<InsurancePolicy> findByDateOfIssue(Date date);
}
