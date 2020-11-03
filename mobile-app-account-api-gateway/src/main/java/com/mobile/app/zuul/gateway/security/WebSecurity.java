package com.mobile.app.zuul.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private Environment env;

	@Override
	public void configure(HttpSecurity http)
			throws Exception {
		
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		.antMatchers(env.getProperty("api.zuul.actuator.url.path")).permitAll()
		.antMatchers(env.getProperty("api.h2.console.path")).permitAll()
		.antMatchers(HttpMethod.POST,env.getProperty("api.login.url.path")).permitAll()
		.antMatchers(HttpMethod.POST, env.getProperty("api.registration.url.path")).permitAll()	
		.anyRequest().authenticated()
		.and()
		.addFilter(new AutheraizationFilter(env ,authenticationManager()));
		;
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}

	
	
	
	
}
