package com.ftn.repository;

import com.ftn.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findById(Long id);

    Optional<Participant> findByCustomerId(Long id);

    Optional<Participant> findByInsuredByPolicyId(Long id);

    Optional<Participant> findByEmail(String email);
}
