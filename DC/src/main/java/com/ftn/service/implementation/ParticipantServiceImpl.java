package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;

import com.ftn.model.Participant;
import com.ftn.model.dto.ParticipantDTO;
import com.ftn.repository.ParticipantRepository;
import com.ftn.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository insuredRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository insuredRepository){
        this.insuredRepository = insuredRepository;
    }

    @Override
    public List<ParticipantDTO> readAll() {
        return insuredRepository.findAll().stream().map(ParticipantDTO::new).collect(Collectors.toList());

    }

    @Override
    public ParticipantDTO create(ParticipantDTO insuredDTO) {
        final Participant insured = insuredDTO.construct();
        insuredRepository.save(insured);
        return new ParticipantDTO(insured);
    }

    @Override
    public ParticipantDTO update(Long id, ParticipantDTO insuredDTO) {
        final Participant insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        insured.merge(insuredDTO);
        insuredRepository.save(insured);
        return new ParticipantDTO(insured);
    }

    @Override
    public void delete(Long id) {
        final Participant insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        insuredRepository.delete(insured);
    }

    @Override
    public ParticipantDTO findById(Long id) {
        final Participant insured = insuredRepository.findById(id).orElseThrow(NotFoundException::new);
        return new ParticipantDTO(insured);
    }

    @Override
    public ParticipantDTO findByCustomerId(Long id) {
        final Participant insured = insuredRepository.findByCustomerId(id).orElseThrow(NotFoundException::new);
        return new ParticipantDTO(insured);
    }

    @Override
    public ParticipantDTO findByPolicyId(Long id) {
        final Participant insured = insuredRepository.findByInsuredByPolicyId(id).orElseThrow(NotFoundException::new);
        return new ParticipantDTO(insured);
    }
}
