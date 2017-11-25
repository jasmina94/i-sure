package com.ftn.service.implementation;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.service.RiskTypeService;
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
public class RiskTypeServiceImpl implements RiskTypeService {

    @Override
    public List<RiskTypeDTO> readAll() {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskTypeDTO[]> response = restTemplate.getForEntity(uri, RiskTypeDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskTypeDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(riskTypeDTO),
                RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskTypeDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH,
                new HttpEntity<>(riskTypeDTO), RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }

    @Override
    public RiskTypeDTO findById(Long id) {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(uri, RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO findByName(String name) {
        String uri = Paths.DATA_CENTER_RISK_TYPE_URI + "/byUcn/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(uri, RiskTypeDTO.class);

        return response.getBody();
    }
}
