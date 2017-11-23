package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.repository.InsurancePolicyRepository;
import com.ftn.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    public InsurancePolicyServiceImpl(InsurancePolicyRepository insurancePolicyRepository){
        this.insurancePolicyRepository = insurancePolicyRepository;
    }

    @Override
    public List<InsurancePolicyDTO> readAll() {
        return insurancePolicyRepository.findAll().stream().map(InsurancePolicyDTO::new).collect(Collectors.toList());
    }

    @Override
    public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {
        final InsurancePolicy insurancePolicy = insurancePolicyDTO.construct();
        insurancePolicyRepository.save(insurancePolicy);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);
        insurancePolicy.merge(insurancePolicyDTO);
        insurancePolicyRepository.save(insurancePolicy);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public void delete(Long id) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);
        insurancePolicyRepository.delete(insurancePolicy);
    }

    @Override
    public InsurancePolicyDTO findById(Long id) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public InsurancePolicyDTO findByDateOfIssue(Date date) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findByDateOfIssue(date).orElseThrow(NotFoundException::new);
        return new InsurancePolicyDTO(insurancePolicy);
    }
}
