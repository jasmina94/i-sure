package com.ftn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.model.Transaction;


public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	public Optional<Transaction> findById(Long id);
	
	@Query("select t from Transaction t where t.payment.paymentServiceId = ?1")
	public Optional<Transaction> findByPaymentId(String paymentId);
}
