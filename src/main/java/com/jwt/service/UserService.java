package com.jwt.service;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.dao.RoleDao;
import com.jwt.dao.UserDao;
import com.jwt.model.Role;
import com.jwt.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	PasswordEncoder encoder;
	
	public User registerNewUser(User user) {
		return dao.save(user);
	}
	
	public void initRolesAndUser() {
		Role admin = new Role();
		admin.setRoleName("Admin");
		admin.setRoleDescription("Admin role");
		roleDao.save(admin);
		
		Role user = new Role();
		user.setRoleName("User");
		user.setRoleDescription("User role");
		roleDao.save(user);
		
		User adminUser = new User();
		adminUser.setUserName("admin123");
		adminUser.setFirstName("admin");
		adminUser.setLastName("admins");
		adminUser.setPassword(getEncodedPassword("admins@123"));
		Set<Role> adminRole = new HashSet<>();
		adminRole.add(admin);
		adminUser.setRole(adminRole);
		dao.save(adminUser);
		
		User users = new User();
		users.setFirstName("Ritik");
		users.setLastName("Sharma");
		users.setUserName("ritik123");
		users.setPassword(getEncodedPassword("user@123"));
		Set<Role> userRole = new HashSet<>();
		userRole.add(user);
		users.setRole(userRole);
		dao.save(users);
		
	}
	
	public String getEncodedPassword(String password) {
		return encoder.encode(password);
	}

}
