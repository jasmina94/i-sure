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

import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Value("${dc.home}")
    private String home;
	
	@Value("${dc.transactions}")
	private String transactions;
	
	@Override
	public List<TransactionDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionDTO[]> response = restTemplate.getForEntity(home + transactions, TransactionDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public TransactionDTO create(TransactionDTO transactionDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionDTO> response = restTemplate.postForEntity(home + transactions, new HttpEntity<>(transactionDTO),
                TransactionDTO.class);

        return response.getBody();
	}

	@Override
	public TransactionDTO update(Long id, TransactionDTO transactionDTO) {
		String URI = home + transactions + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<TransactionDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(transactionDTO), TransactionDTO.class);

        return response.getBody();

	}

	@Override
	public void delete(Long id) {
		String URI = home + transactions + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
	}

	@Override
	public TransactionDTO findById(Long id) {
        String URI = home + transactions + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionDTO> response = restTemplate.getForEntity(URI, TransactionDTO.class);

        return response.getBody();
	}
}
