package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.configurations.Paths;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.service.RoadsideAssistanceInsuranceService;

@Service
public class RoadsideAssistanceInsuranceServiceImpl implements RoadsideAssistanceInsuranceService{

	@Override
	public List<RoadsideAssistanceInsuranceDTO> readAll() {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI;
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.postForEntity(uri, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
	}

	@Override
	public RoadsideAssistanceInsuranceDTO update(Long id,
			RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
	}

	@Override
	public void delete(Long id) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/"+id;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(uri);
	}

	@Override
	public RoadsideAssistanceInsuranceDTO findById(Long id) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO.class);

    	return response.getBody();
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByUcn(String ucn) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byUcn/"+ucn;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byYearOfManufacture/"+yearOfManufacture;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byLicencePlateNumber/"+licencePlateNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byUndercarriageNumber/"+undercarriageNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byCarBrand/"+carBrand;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType) {
		String uri = Paths.DATA_CENTER_ROADSIDE_ASSISTANCE_INSURANCE_URI+"/byCarType/"+carType;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(uri, RoadsideAssistanceInsuranceDTO[].class);

    	return Arrays.asList(response.getBody());
	}


   
}
