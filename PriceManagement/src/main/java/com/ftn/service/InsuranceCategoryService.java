package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.InsuranceCategoryDTO;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface InsuranceCategoryService {

    List<InsuranceCategoryDTO> readAll();

    InsuranceCategoryDTO create(InsuranceCategoryDTO insuranceCategoryDTO);

    InsuranceCategoryDTO update(Long id, InsuranceCategoryDTO insuranceCategoryDTO);

    void delete(Long id);

    InsuranceCategoryDTO findById(Long id);

    InsuranceCategoryDTO findByName(String name);
}
