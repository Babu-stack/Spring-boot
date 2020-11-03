package com.appdeveloperblog.app.ws.controller;

import org.apache.catalina.User;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblog.ws.model.UsersRest;

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersRest> getuser(@RequestParam(value="page",required = false) String page
			                                 , HttpStatus s) {
		

		UsersRest user =new UsersRest();
		user.setId("25");
		user.setAge("52");
		user.setName("vijay");
		user.setEmail("vj@hotmail.com");
		
		return new ResponseEntity<UsersRest>(user,s.OK);
	}
	
	
	@GetMapping(path= "/{userId}")
	public String getUsers(@PathVariable int userId) {
		
		return "get users"+userId;
	}
	
	@PostMapping
	public String postusers() {
		
		
		return "post users";
		
	}
	
}
