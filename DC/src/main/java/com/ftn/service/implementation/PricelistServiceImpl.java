package com.ftn.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Customer;
import com.ftn.model.Pricelist;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.PricelistDTO;
import com.ftn.repository.PricelistRepository;
import com.ftn.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService{
	
    private final PricelistRepository pricelistRepository;

    @Autowired
    public PricelistServiceImpl(PricelistRepository pricelistRepository){
        this.pricelistRepository = pricelistRepository;
    }
	
	@Override
	public List<PricelistDTO> readAll() {
		return pricelistRepository.findAll().stream().map(PricelistDTO::new).collect(Collectors.toList());
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {
		final Pricelist pricelist = pricelistDTO.construct();
		pricelistRepository.save(pricelist);
        return new PricelistDTO(pricelist);
	}

	@Override
	public PricelistDTO update(Long id, PricelistDTO pricelistDTO) {
		final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
		pricelist.merge(pricelistDTO);
		pricelistRepository.save(pricelist);
        return new PricelistDTO(pricelist);
	}

	@Override
	public void delete(Long id) {
        final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
        pricelistRepository.delete(pricelist);		
	}

	@Override
	public PricelistDTO findById(Long id) {
        final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
        return new PricelistDTO(pricelist);
	}

}
