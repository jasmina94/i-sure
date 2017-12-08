package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.RiskType;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.repository.RiskRepository;
import com.ftn.repository.RiskTypeRepository;
import com.ftn.service.RiskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskTypeServiceImpl implements RiskTypeService{

    private final RiskTypeRepository riskTypeRepository;

    @Autowired
    public RiskTypeServiceImpl(RiskTypeRepository riskTypeRepository){
        this.riskTypeRepository = riskTypeRepository;
    }

    @Override
    public List<RiskTypeDTO> readAll() {
        return riskTypeRepository.findAll().stream().map(RiskTypeDTO::new).collect(Collectors.toList());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {
        final RiskType riskType = riskTypeDTO.construct();
        riskTypeRepository.save(riskType);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        riskType.merge(riskTypeDTO);
        riskTypeRepository.save(riskType);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public void delete(Long id) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        riskTypeRepository.delete(riskType);
    }

    @Override
    public RiskTypeDTO findById(Long id) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public RiskTypeDTO findByName(String name) {
        final RiskType riskType = riskTypeRepository.findByRiskTypeName(name).orElseThrow(NotFoundException::new);
        return new RiskTypeDTO(riskType);
    }
}
