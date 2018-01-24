package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;

import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.service.RiskTypeService;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskTypeServiceImpl implements RiskTypeService {

    @Value("${dc.risk.type}")
    private String URI;
    
    private KeycloakRestTemplate restTemplate = new KeycloakRestTemplate(new KeycloakClientRequestFactory());
    
    @Override
    public List<RiskTypeDTO> readAll() {
        ResponseEntity<RiskTypeDTO[]> response = restTemplate.getForEntity(URI, RiskTypeDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {
        ResponseEntity<RiskTypeDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(riskTypeDTO),
                RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {
        URI += "/" + id;
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskTypeDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(riskTypeDTO), RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        URI += "/" + id;
        restTemplate.delete(URI);
    }

    @Override
    public RiskTypeDTO findById(Long id) {
        URI += "/" + id;
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(URI, RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO findByName(String name) {
        URI += "/byPersonalId/" + name;
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(URI, RiskTypeDTO.class);

        return response.getBody();
    }
}
