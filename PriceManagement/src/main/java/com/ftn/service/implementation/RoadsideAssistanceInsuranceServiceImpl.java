package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.service.RoadsideAssistanceInsuranceService;

@Service
public class RoadsideAssistanceInsuranceServiceImpl implements RoadsideAssistanceInsuranceService {

    @Value("${dc.roadside.assistance.insurance}")
    private String URI;

    @Override
    public List<RoadsideAssistanceInsuranceDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public RoadsideAssistanceInsuranceDTO update(Long id,
                                                 RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
    }

    @Override
    public RoadsideAssistanceInsuranceDTO findById(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByPersonalId(String personalId) {
        URI += "/byPersonalId/" + personalId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture) {
        URI += "/byYearOfManufacture/" + yearOfManufacture;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber) {
        URI += "/byLicencePlateNumber/" + licencePlateNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber) {
        URI += "/byUndercarriageNumber/" + undercarriageNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand) {
        URI += "/byCarBrand/" + carBrand;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType) {
        URI += "/byCarType/" + carType;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
