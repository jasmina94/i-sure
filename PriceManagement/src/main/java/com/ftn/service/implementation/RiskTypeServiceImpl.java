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

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.risk.type}")
    private String dc_risk_type;
    
    private KeycloakRestTemplate restTemplate = new KeycloakRestTemplate(new KeycloakClientRequestFactory());
    
    @Override
    public List<RiskTypeDTO> readAll() {
        ResponseEntity<RiskTypeDTO[]> response = restTemplate.getForEntity(dc_home + dc_risk_type, RiskTypeDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {
        ResponseEntity<RiskTypeDTO> response = restTemplate.postForEntity(dc_home + dc_risk_type, new HttpEntity<>(riskTypeDTO),
                RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskTypeDTO> response = restTemplate.exchange(dc_home + dc_risk_type + "/" + id, HttpMethod.PATCH,
                new HttpEntity<>(riskTypeDTO), RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(dc_home + dc_risk_type + "/" + id);
    }

    @Override
    public RiskTypeDTO findById(Long id) {
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(dc_home + dc_risk_type + "/" + id, RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO findByName(String name) {
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(dc_home + dc_risk_type + "/byPersonalId/" + name, RiskTypeDTO.class);

        return response.getBody();
    }
}
