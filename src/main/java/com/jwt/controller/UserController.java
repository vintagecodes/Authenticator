package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.User;
import com.jwt.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@javax.annotation.PostConstruct
	public void initRoleAndUser() {
		service.initRolesAndUser();
	}
	
	@PostMapping("/userPost")
	public User registerNewUser(@RequestBody User user) {
		return service.registerNewUser(user);
		
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "THis URL is only accessible to admin";
	}
	
	
	@GetMapping("/forUser")
	public String forUser() {
		return "THis URL is only accessible to users";
	}
	

}
