package com.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.Util.JwtUtil;
import com.jwt.dao.UserDao;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
		String userName = jwtRequest.getUserName();
		String pass = jwtRequest.getPassword();
		authenticate(userName, pass);
		
		final UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		com.jwt.model.User user = userDao.findById(userName).get();
		
		return new JwtResponse(user, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.jwt.model.User user =  userDao.findById(username).get();
		if(user!=null) {
			return new User(user.getUserName(), user.getPassword(), getAuthorities(user));
		} else {
			throw new UsernameNotFoundException("Username is not valid");
		}
		
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(com.jwt.model.User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		
		user.getRole().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		
		return authorities;
	}
	
	private void authenticate(String userName, String password) throws Exception{
		 
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("User is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials from user");
		}
		
		
	}

}
