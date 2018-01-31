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

import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskServiceImpl implements RiskService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.risk}")
    private String dc_risk;
    
    private KeycloakRestTemplate restTemplate = new KeycloakRestTemplate(new KeycloakClientRequestFactory());
    
    @Override
    public List<RiskDTO> readAll() {
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity(dc_home + dc_risk, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskDTO[] create(RiskDTO riskDTO) {
        ResponseEntity<RiskDTO[]> response = restTemplate.postForEntity(dc_home + dc_risk, new HttpEntity<>(riskDTO),
                RiskDTO[].class);

        return response.getBody();
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskDTO> response = restTemplate.exchange(dc_home + dc_risk + "/" + id, HttpMethod.PATCH,
                new HttpEntity<>(riskDTO), RiskDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(dc_home + dc_risk + "/" + id);
    }

    @Override
    public RiskDTO findById(Long id) {        
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(dc_home + dc_risk + "/" + id, RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO findByName(String name) {
       
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity( dc_home + dc_risk + "/byPersonalId/" + name, RiskDTO.class);

        return response.getBody();
    }
    
    @Override
    public List<RiskDTO> findByRiskType(Long id) {
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity( dc_home + dc_risk + "/byRiskType/" + id, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }
}
