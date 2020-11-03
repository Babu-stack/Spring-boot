package com.mobile.app.zuul.gateway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AutheraizationFilter extends BasicAuthenticationFilter {

	private Environment env;
	
	
	 public AutheraizationFilter(Environment env,AuthenticationManager auth) {
	
		 super(auth);
		 this.env=env;		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String autherizationHeader=request.getHeader(env.getProperty("autherization.token.name"));
		
		
		if(autherizationHeader==null || ! autherizationHeader.startsWith(env.getProperty("autherization.token.prefix")))
		{
			
			chain.doFilter(request, response);
			//response.sendError(400, "Invalid Token");
			return;
		}
		UsernamePasswordAuthenticationToken authentication =getAuthentication(request,autherizationHeader);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

		
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req,String authHeader) {
	 
	 
	 if(authHeader==null) {
		 
		 return null;
	 }
	 
	 String token =authHeader.replace(env.getProperty("autherization.token.prefix"), "");
	 System.out.println("token "+token);
	 String userId =Jwts.parser()
			.setSigningKey(env.getProperty("token.secret"))
	 		.parseClaimsJws(token)
	 		.getBody()
	 		.getSubject();	
	 
	 
	 if(userId==null) {
		 
		 return null;
	 }
	 return new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
	 
	 
	 
 }
	
}
