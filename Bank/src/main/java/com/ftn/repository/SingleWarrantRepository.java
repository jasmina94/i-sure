package com.ftn.repository;

import com.ftn.model.database.SingleWarrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface SingleWarrantRepository extends JpaRepository<SingleWarrant, Long> {

    Optional<SingleWarrant> findById(Long id);

}
