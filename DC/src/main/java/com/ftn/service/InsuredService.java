package com.ftn.service;

import com.ftn.model.dto.InsuredDTO;

import java.util.List;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface InsuredService {

    List<InsuredDTO> readAll();

    InsuredDTO create(InsuredDTO insuredDTO);

    InsuredDTO update(Long id, InsuredDTO insuredDTO);

    void delete(Long id);

    InsuredDTO findById(Long id);

    InsuredDTO findByCustomerId(Long id);

    InsuredDTO findByPolicyId(Long id);
}
