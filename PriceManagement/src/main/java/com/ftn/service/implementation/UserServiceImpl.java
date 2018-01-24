package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.UserDTO;
import com.ftn.service.UserService;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Service
public class UserServiceImpl implements UserService{

    @Value("${dc.adress}")
    private String URI;

    @Override
    public UserDTO getUserById(Long id) {
        URI += "/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(URI, UserDTO.class);

        if(response == null) {
            System.out.println("Response is null.");
        }else {
            System.out.println("Response is not null.");
            System.out.println(response.getBody());
        }

        return response.getBody();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO getUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
