package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.resolver.ResponseHandler;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.model.dto.TransactionStatus;
import com.ftn.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Value("${dc.home}")
    private String dc_home;
	
	@Value("${dc.transactions}")
	private String dc_transactions;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public ResponseEntity readAll() {
        ResponseEntity<TransactionDTO[]> response = restTemplate.getForEntity(dc_home + dc_transactions, TransactionDTO[].class);
        ResponseHandler.checkResponseStatus(response.getStatusCode());
        return response;
	}

	@Override
	public ResponseEntity create(TransactionDTO transactionDTO) {
		transactionDTO.setStatus(TransactionStatus.PENDING);
        ResponseEntity<TransactionDTO> response = restTemplate.postForEntity(dc_home + dc_transactions, new HttpEntity<>(transactionDTO),
                TransactionDTO.class);
        ResponseHandler.checkResponseStatus(response.getStatusCode());
        return response;
	}

	@Override
	public ResponseEntity update(Long id, TransactionDTO transactionDTO) {
		String URI = dc_home + dc_transactions + "/" + id;
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate.setRequestFactory(requestFactory);
        ResponseEntity<TransactionDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(transactionDTO), TransactionDTO.class);
        ResponseHandler.checkResponseStatus(response.getStatusCode());
        return response;
	}

	@Override
	public void delete(Long id) {
		String URI = dc_home + dc_transactions + "/" + id;
        restTemplate.delete(URI);
	}

	@Override
	public ResponseEntity findById(Long id) {
        String URI = dc_home + dc_transactions + "/" + id;
        ResponseEntity<TransactionDTO> response = restTemplate.getForEntity(URI, TransactionDTO.class);
        ResponseHandler.checkResponseStatus(response.getStatusCode());
        return response;
	}

	@Override
	public ResponseEntity findByPaymentId(String paymentId) {
        String URI = dc_home + dc_transactions + "/payment/" + paymentId;
        ResponseEntity<TransactionDTO> response = restTemplate.getForEntity(URI, TransactionDTO.class);
        ResponseHandler.checkResponseStatus(response.getStatusCode());
        return response;
    }
}
