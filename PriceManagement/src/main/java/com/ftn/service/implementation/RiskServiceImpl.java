package com.ftn.service.implementation;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${dc.risk}")
    private String URI;

    @Override
    public List<RiskDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity(URI, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskDTO create(RiskDTO riskDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(riskDTO),
                RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(riskDTO), RiskDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
    }

    @Override
    public RiskDTO findById(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(URI, RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO findByName(String name) {
        URI += "/byPersonalId/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(URI, RiskDTO.class);

        return response.getBody();
    }
}
