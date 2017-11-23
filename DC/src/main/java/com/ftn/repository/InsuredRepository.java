package com.ftn.repository;

import com.ftn.model.Insured;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface InsuredRepository extends JpaRepository<Insured, Long> {

    Optional<Insured> findById(Long id);

    Optional<Insured> findByInsuredCustomerId(Long id);

    Optional<Insured> findByInsuredByPolicyId(Long id);

    Optional<Insured> findByEmail(String email);
}
