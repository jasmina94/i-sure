package com.ftn.service.implementation;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.service.InsuranceCategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class InsuranceCategoryServiceImpl implements InsuranceCategoryService {

    @Override
    public List<InsuranceCategoryDTO> readAll() {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO[]> response = restTemplate.getForEntity(uri, InsuranceCategoryDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsuranceCategoryDTO create(InsuranceCategoryDTO insuranceCategoryDTO) {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(insuranceCategoryDTO),
                InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO update(Long id, InsuranceCategoryDTO insuranceCategoryDTO) {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH,
                new HttpEntity<>(insuranceCategoryDTO), InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }

    @Override
    public InsuranceCategoryDTO findById(Long id) {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(uri, InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO findByName(String name) {

        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI + "/byUcn/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(uri, InsuranceCategoryDTO.class);

        return response.getBody();
    }
}
