package com.ftn.service.implementation;

import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.service.HomeInsuranceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService {

	@Value("${dc.home.insurance}")
	private String URI;

	@Override
	public List<HomeInsuranceDTO> readAll() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(URI, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public HomeInsuranceDTO create(HomeInsuranceDTO homeInsuranceDTO) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(homeInsuranceDTO),
				HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public HomeInsuranceDTO update(Long id, HomeInsuranceDTO homeInsuranceDTO) {

		URI += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<HomeInsuranceDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
				new HttpEntity<>(homeInsuranceDTO), HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		URI += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URI);
	}

	@Override
	public HomeInsuranceDTO findById(Long id) {

		URI += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO> response = restTemplate.getForEntity(URI, HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public List<HomeInsuranceDTO> findByPersonalId(String personalId) {

		URI += "/byPersonalId/" + personalId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(URI, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}
}
