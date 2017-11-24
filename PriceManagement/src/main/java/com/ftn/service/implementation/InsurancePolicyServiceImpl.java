package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.InsurancePolicyService;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService{

	@Override
	public List<InsurancePolicyDTO> readAll() {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI ;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(uri, InsurancePolicyDTO[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI ;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<InsurancePolicyDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(insurancePolicyDTO),
				InsurancePolicyDTO.class);

		return response.getBody();
	}

	@Override
	public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI  + "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<InsurancePolicyDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH,
				new HttpEntity<>(insurancePolicyDTO), InsurancePolicyDTO.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI  + "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri);
	}

	@Override
	public InsurancePolicyDTO findById(Long id) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI  + "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<InsurancePolicyDTO> response = restTemplate.getForEntity(uri, InsurancePolicyDTO.class);

		return response.getBody();
	}

	@Override
	public List<InsurancePolicyDTO> findByDateOfIssue(Date date) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI  + "/byDateOfIssue/" + date;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(uri, InsurancePolicyDTO[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public List<InsurancePolicyDTO> findByDateBecomeEffective(Date date) {
		String uri = Paths.DATA_CENTER_INSURANCE_POLICY_URI  + "/byDateBecomeEffective/" + date;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(uri, InsurancePolicyDTO[].class);

		return Arrays.asList(response.getBody());
	}

	
	
	
}
