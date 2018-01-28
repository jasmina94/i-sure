package com.ftn.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import com.ftn.service.UsersEmailService;

@Service
public class UsersEmailServiceImpl implements UsersEmailService{

	@Override
	public List<String> read() {
		Keycloak keycloak = Keycloak.getInstance(
    		    "http://localhost:8080/auth",
    		    "master",
    		    "admin",
    		    "admin",
    		    "admin-cli");
    	
    	Set<UserRepresentation> users = keycloak.realms().realm("ISure").roles().get("salesman").getRoleUserMembers();
    	
    	List<String> emails = new ArrayList<String>();
    	for(UserRepresentation user : users) {
    			emails.add(user.getEmail());
    	}
    	
		return emails;
	}

}
