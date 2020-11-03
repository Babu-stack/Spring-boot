package com.mobile.app.account.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobileAppAccountWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppAccountWsApplication.class, args);
	}

}
