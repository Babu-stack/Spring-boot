package com.mobile.app.user.ws.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.app.user.ws.model.UserLoginModel;
import com.mobile.app.user.ws.service.UserService;
import com.mobile.app.user.ws.shared.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class Authenticationfilter extends UsernamePasswordAuthenticationFilter{

	
	private UserService userService;
	private Environment environment;
	
	
	
	public Authenticationfilter() {
		super();
	}


    public Authenticationfilter(UserService userService, Environment environment, 
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
			
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginModel creds = new ObjectMapper()
                                 .readValue(request.getInputStream(), UserLoginModel.class);
			
			
				
			
			return getAuthenticationManager().authenticate
					(new UsernamePasswordAuthenticationToken
							(creds.getEmail(),
							 creds.getPassWord()));    
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
	
	}
	

	
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
		
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDto userDto = userService.getUserDetailsByEmail(userName);

        String token = Jwts.builder()
                .setSubject(userDto.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
                .compact();
        System.out.println(environment.getProperty("token.expiration_time"));
        res.addHeader("token", token);
        res.addHeader("userId", userDto.getUserId());
		
		
		
	}
	
	
}
