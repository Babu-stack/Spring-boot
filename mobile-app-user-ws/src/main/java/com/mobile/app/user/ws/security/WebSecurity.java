package com.mobile.app.user.ws.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobile.app.user.ws.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	
	private Environment env;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	
		this.env = env;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("conigure");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.and()
		.addFilter(getAuthenticationUserFilter());
		http.headers().frameOptions().disable();
		
	}
	//
	
	private Authenticationfilter getAuthenticationUserFilter() throws Exception {
			Authenticationfilter authenticationfilter =new Authenticationfilter(userService , env , authenticationManager());
			
			authenticationfilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
			//authenticationfilter.setAuthenticationManager(authenticationManager());
			return authenticationfilter;
		
	}

	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder); 
		
	}

	
	
	
	
	
	
}
