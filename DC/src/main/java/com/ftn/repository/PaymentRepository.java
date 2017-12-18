package com.ftn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.Payment;
import com.ftn.model.dto.PaymentDTO;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	public Optional<Payment> findById(Long id);
}
