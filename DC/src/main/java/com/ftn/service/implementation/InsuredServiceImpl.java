package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;

import com.ftn.model.Insured;
import com.ftn.model.dto.InsuredDTO;
import com.ftn.repository.InsuredRepository;
import com.ftn.service.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Service
public class InsuredServiceImpl implements InsuredService {

    private final InsuredRepository insuredRepository;

    @Autowired
    public InsuredServiceImpl(InsuredRepository insuredRepository){
        this.insuredRepository = insuredRepository;
    }

    @Override
    public List<InsuredDTO> readAll() {
        return insuredRepository.findAll().stream().map(InsuredDTO::new).collect(Collectors.toList());

    }

    @Override
    public InsuredDTO create(InsuredDTO insuredDTO) {
        final Insured insured = insuredDTO.construct();
        insuredRepository.save(insured);
        return new InsuredDTO(insured);
    }

    @Override
    public InsuredDTO update(Long id, InsuredDTO insuredDTO) {
        final Insured insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        insured.merge(insuredDTO);
        insuredRepository.save(insured);
        return new InsuredDTO(insured);
    }

    @Override
    public void delete(Long id) {
        final Insured insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        insuredRepository.delete(insured);
    }

    @Override
    public InsuredDTO findById(Long id) {
        final Insured insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        return new InsuredDTO(insured);
    }

    @Override
    public InsuredDTO findByCustomerId(Long id) {
        final Insured insured = insuredRepository.findByInsuredCustomerId(id).orElseThrow(NotFoundException::new);
        return new InsuredDTO(insured);
    }

    @Override
    public InsuredDTO findByPolicyId(Long id) {
        final Insured insured = insuredRepository.findByInsuredByPolicyId(id).orElseThrow(NotFoundException::new);
        return new InsuredDTO(insured);
    }
}
