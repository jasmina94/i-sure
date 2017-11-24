package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.service.InternationalTravelInsuranceService;

@Service
public class InternationalTravelInsuranceServiceImpl implements InternationalTravelInsuranceService{

	@Override
	public List<InternationalTravelInsuranceDTO> readAll() {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(uri, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
	}

	@Override
	public InternationalTravelInsuranceDTO update(Long id,
			InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/"+id;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(uri);
	}

	@Override
	public InternationalTravelInsuranceDTO findById(Long id) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.getForEntity(uri, InternationalTravelInsuranceDTO.class);

    	return response.getBody();
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByIssueDate(Date date) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/byIssueDate/"+date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(uri, InternationalTravelInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/byNumberOfPersons/"+numberOfPersons;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(uri, InternationalTravelInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays) {
		String uri = Paths.DATA_CENTER_INTERNATIONAL_TRAVEL_INSURANCE_URI+"/byDurationsInDays/"+durationInDays;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(uri, InternationalTravelInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	

}
