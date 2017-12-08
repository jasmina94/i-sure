package com.ftn.repository;

import com.ftn.model.database.Mt102Model;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface Mt102Repository extends JpaRepository<Mt102Model, Long> {

    Optional<Mt102Model> findById(Long id);

    List<Mt102Model> findBySwiftDebtorBankAndSwiftCreditorBankAndSent(String swiftBankDebtor, String swiftBankCreditor, boolean isSent);

    Optional<Mt102Model> findByMessageId(String messageId);

}
