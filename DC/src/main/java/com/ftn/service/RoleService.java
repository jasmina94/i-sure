package com.ftn.service;

import java.util.List;

import com.ftn.model.authorisation.Role;

public interface RoleService {

	List<Role> readAll();

	Role create(Role Role);

	Role update(Long id, Role Role);

	void delete(Long id);

	Role findById(Long id);

	Role findByName(String name);


}
