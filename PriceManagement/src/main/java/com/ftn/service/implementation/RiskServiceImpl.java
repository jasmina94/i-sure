package com.ftn.service.implementation;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;
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
public class RiskServiceImpl implements RiskService {

    @Override
    public List<RiskDTO> readAll() {
        String uri = Paths.DATA_CENTER_RISK_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity(uri, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskDTO create(RiskDTO riskDTO) {
        String uri = Paths.DATA_CENTER_RISK_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(riskDTO),
                RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {
        String uri = Paths.DATA_CENTER_RISK_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH,
                new HttpEntity<>(riskDTO), RiskDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        String uri = Paths.DATA_CENTER_RISK_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }

    @Override
    public RiskDTO findById(Long id) {
        String uri = Paths.DATA_CENTER_RISK_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(uri, RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO findByName(String name) {
        String uri = Paths.DATA_CENTER_INSURANCE_CATEGORY_URI + "/byUcn/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(uri, RiskDTO.class);

        return response.getBody();
    }
}
