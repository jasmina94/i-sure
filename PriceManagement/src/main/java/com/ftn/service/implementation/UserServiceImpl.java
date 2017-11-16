package com.ftn.service.implementation;

import com.ftn.model.dto.UserDTO;
import com.ftn.service.UserService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Service
public class UserServiceImpl implements UserService{

    private static String DATA_CENTER_ADDRESS = "http://localhost:8080/api/users";

    @Override
    public UserDTO getUserById(Long id) {
        String uri = DATA_CENTER_ADDRESS + "/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(uri, UserDTO.class);

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
