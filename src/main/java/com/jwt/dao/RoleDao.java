package com.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String>{
	
	
	

}
