package com.ftn.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.Payment;
import com.ftn.model.Transaction;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.repository.InsurancePolicyRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final TransactionRepository transactionRepository;
	private final PaymentRepository paymentRepository;
	private final InsurancePolicyRepository insurancePolicyRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			PaymentRepository paymentRepository, InsurancePolicyRepository insurancePolicyRepository) {
		this.transactionRepository = transactionRepository;
		this.paymentRepository = paymentRepository;
		this.insurancePolicyRepository = insurancePolicyRepository;
	}
	
	@Override
	public List<TransactionDTO> readAll() {
		return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
	}

	@Override
	public TransactionDTO create(TransactionDTO transactionDTO) {
		final Transaction transaction = transactionDTO.construct();
		
		InsurancePolicy insurancePolicy = transaction.getInsurancePolicy();
		if(insurancePolicy != null) {
			insurancePolicy = insurancePolicyRepository.save(insurancePolicy);
        	transaction.setInsurancePolicy(insurancePolicy);
		}
		
		Transaction retVal = transactionRepository.save(transaction);
		return new TransactionDTO(retVal);
	}

	@Override
	public TransactionDTO update(Long id, TransactionDTO transactionDTO) {
		final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);
		transaction.merge(transactionDTO);
		
		Transaction retVal = transactionRepository.save(transaction);
		return new TransactionDTO(retVal);
	}

	@Override
	public void delete(Long id) {
        final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);

        transaction.setActive(false);
        transactionRepository.save(transaction);
	}

	@Override
	public TransactionDTO findById(Long id) {
		final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);
        return new TransactionDTO(transaction);
	}

}
