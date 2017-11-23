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
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.service.HomeInsuranceService;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService {

    @Override
    public List<HomeInsuranceDTO> readAll() {
        
    	String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(uri, HomeInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public HomeInsuranceDTO create(HomeInsuranceDTO homeInsuranceDTO) {
        
    	String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HomeInsuranceDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(homeInsuranceDTO), HomeInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public HomeInsuranceDTO update(Long id, HomeInsuranceDTO homeInsuranceDTO) {
     
    	String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<HomeInsuranceDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(homeInsuranceDTO), HomeInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
    	String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI+"/"+id;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(uri);
    }

    @Override
    public HomeInsuranceDTO findById(Long id) {
        
    	String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HomeInsuranceDTO> response = restTemplate.getForEntity(uri, HomeInsuranceDTO.class);

    	return response.getBody();
    }

   
	@Override
	public HomeInsuranceDTO findByUcn(String ucn) {
		
		String uri = Paths.DATA_CENTER_HOME_INSURANCE_URI+"/byUcn/"+ucn;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HomeInsuranceDTO> response = restTemplate.getForEntity(uri, HomeInsuranceDTO.class);

    	return response.getBody();
	}
}
