package com.ftn.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.service.UserService;
import com.ftn.service.UsersEmailService;

@RestController
@RequestMapping("/emails")
public class UsersEmailController {
	
    private final UsersEmailService usersEmailService;

    @Autowired
    public UsersEmailController(UsersEmailService usersEmailService){
        this.usersEmailService = usersEmailService;
    }
	
	@Transactional
    @GetMapping
    public ResponseEntity read() {
    	
    	return new ResponseEntity<>(usersEmailService.read(), HttpStatus.OK);
    }
}
