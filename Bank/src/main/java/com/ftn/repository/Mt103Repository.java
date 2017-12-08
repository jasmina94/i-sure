package com.ftn.repository;

import com.ftn.model.database.Mt103Model;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface Mt103Repository extends JpaRepository<Mt103Model, Long> {

    Optional<Mt103Model> findById(Long id);

    Optional<Mt103Model> findByMessageId(String messageId);
}
