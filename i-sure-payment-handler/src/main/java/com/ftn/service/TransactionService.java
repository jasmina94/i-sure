package com.ftn.service;

import org.springframework.http.ResponseEntity;

import com.ftn.model.dto.TransactionDTO;

public interface TransactionService {
    ResponseEntity readAll();

    ResponseEntity create(TransactionDTO transactionDTO);

    ResponseEntity update(Long id, TransactionDTO transactionDTO);

    void delete(Long id);

    ResponseEntity findById(Long id);
    
    ResponseEntity findByPaymentId(String paymentId);
}
