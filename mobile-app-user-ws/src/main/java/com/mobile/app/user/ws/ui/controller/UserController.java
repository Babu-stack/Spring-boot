package com.mobile.app.user.ws.ui.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.user.ws.model.UserRequestModel;
import com.mobile.app.user.ws.model.UserResponseModel;
import com.mobile.app.user.ws.service.UserService;
import com.mobile.app.user.ws.shared.UserDto;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;
	@Autowired
	private UserService userService;
	
	@GetMapping("/status/check")
	public String getUser() {
		System.out.println("inside status check");
		return "users working on port "+ env.getProperty("local.server.port");
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userDetails) {
		
		ModelMapper modelMapper =new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); 
		UserDto userDto=modelMapper.map(userDetails, UserDto.class);
						
		UserDto createdValue=userService.createUser(userDto);
		
		UserResponseModel userResponseModel =modelMapper.map(createdValue, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
	}

	@GetMapping(value = "/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){
		System.out.println("inside path");
		UserDto userDto = userService.getUserById(userId);
		
		UserResponseModel userResponseModel =new ModelMapper().map(userDto, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
	}
	
}

