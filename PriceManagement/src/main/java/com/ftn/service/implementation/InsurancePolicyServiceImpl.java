package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.InsurancePolicyService;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    @Value("${dc.insurance.policy}")
    private String URI;

    @Override
    public List<InsurancePolicyDTO> readAll() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(insurancePolicyDTO),
                InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsurancePolicyDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(insurancePolicyDTO), InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
    }

    @Override
    public InsurancePolicyDTO findById(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO> response = restTemplate.getForEntity(URI, InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public List<InsurancePolicyDTO> findByDateOfIssue(Date date) {
        URI += "/byDateOfIssue/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InsurancePolicyDTO> findByDateBecomeEffective(Date date) {
        URI += "/byDateBecomeEffective/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
