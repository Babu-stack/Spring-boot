package com.mobile.app.user.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mobile.app.user.ws.shared.UserDto;
@Service
public interface UserService extends UserDetailsService{

	public UserDto createUser(UserDto userDto);
	
	public UserDto getUserDetailsByEmail(String email);
	
	public UserDto getUserById(String userId);
}
