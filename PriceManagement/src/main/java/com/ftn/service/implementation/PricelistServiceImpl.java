package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.configurations.Paths;
import com.ftn.exception.NotFoundException;
import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService{
	
	//private static String DATA_CENTER_PRICELIST_URI = "http://localhost:8080/api/pricelists";
	
	@Override
	public List<PricelistDTO> findAll() {
        String uri = Paths.DATA_CENTER_HOME + Paths.PRICELISTS;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO[]> response = restTemplate.getForEntity(uri, PricelistDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public PricelistDTO findById(Long id) {
        String uri = Paths.DATA_CENTER_HOME + Paths.PRICELISTS + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO> response = restTemplate.getForEntity(uri, PricelistDTO.class);
        
        return response.getBody();
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {
        String uri = Paths.DATA_CENTER_HOME + Paths.PRICELISTS;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public PricelistDTO update(Long id, PricelistDTO pricelistDTO) {
        String uri = Paths.DATA_CENTER_HOME + Paths.PRICELISTS + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<PricelistDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String uri = Paths.DATA_CENTER_HOME + Paths.PRICELISTS + "/" + id;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(uri);
	}
}
