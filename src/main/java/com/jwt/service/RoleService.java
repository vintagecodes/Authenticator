package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jwt.dao.RoleDao;
import com.jwt.model.Role;

@Service
public class RoleService {
	
	@Autowired
	@Lazy
	private RoleDao roleDao;
	
	public Role createNewRole(Role role) {
		return roleDao.save(role);
		
	}

}
