package com.ftn.service.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Pricelist;
import com.ftn.model.PricelistItem;
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
		
		
		
		if(pricelistDTO.getId() == 0L){
			final Pricelist pricelist = pricelistDTO.construct();
			Date dateFrom  = pricelistRepository.findMaxDateTo();
			pricelist.setDateFrom(dateFrom);
			pricelistRepository.save(pricelist);
	        return new PricelistDTO(pricelist);
		}else{
			Pricelist stari = pricelistRepository.findById(pricelistDTO.getId()).orElseThrow(NotFoundException::new);
			List<PricelistItem> itemsOld = stari.getPricelistItems();
			
			final Pricelist pricelist = pricelistDTO.construct();
			pricelist.setId(0L);
			pricelist.setActive(true);
			pricelist.setCreated(null);
			
			List<PricelistItem> items = new ArrayList();
			PricelistItem temp = null;
			for (PricelistItem pricelistItem : itemsOld) {
				temp = new PricelistItem(pricelistItem);
				temp.setActive(true);
				temp.setCreated(null);
				temp.setId(0L);
				temp.setUpdated(null);
				
				items.add(temp);
			}
			
			pricelist.setPricelistItems(items);
			
			Date dateFrom  = pricelistRepository.findMaxDateTo();
			pricelist.setDateFrom(dateFrom);
			
			pricelistRepository.save(pricelist);
			return new PricelistDTO(pricelist);
			
		}
//		
//		final Pricelist pricelist = pricelistDTO.construct();
//		pricelistRepository.save(pricelist);
//        return new PricelistDTO(pricelist);
		
		
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

	@Override
	public PricelistDTO findcurrentlyActive() {
		
		
	    Date now = new Date();
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	    
	    Date todayWithZeroTime;
		try {
			todayWithZeroTime = formatter.parse(formatter.format(now));
			 System.out.println(todayWithZeroTime);
			 Pricelist pl = pricelistRepository.findCurrentlyActive(todayWithZeroTime).orElseThrow(NotFoundException::new);
			 PricelistDTO plDto = new PricelistDTO(pl);
			 return plDto;
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
        
	}

}
