package com.ftn.service;

import com.ftn.model.dto.ParticipantDTO;

import java.util.List;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface ParticipantService {

    List<ParticipantDTO> readAll();

    ParticipantDTO create(ParticipantDTO insuredDTO);

    ParticipantDTO update(Long id, ParticipantDTO insuredDTO);

    void delete(Long id);

    ParticipantDTO findById(Long id);

    ParticipantDTO findByCustomerId(Long id);

    ParticipantDTO findByPolicyId(Long id);
}
