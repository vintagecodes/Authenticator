package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.User;
import com.jwt.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostConstruct
	public void initRoleAndUser() {
		service.initRolesAndUser();
	}
	
	@PostMapping("/userPost")
	public User registerNewUser(@RequestBody User user) {
		return service.registerNewUser(user);
		
	}

}
