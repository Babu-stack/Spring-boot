package com.mobile.app.kafka.registerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mobile.app.kafka.librayController.LibraryEventController;

@SpringBootApplication
@ComponentScan(basePackageClasses = LibraryEventController.class)
public class KakaAppRegisterserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaAppRegisterserviceApplication.class, args);
	}

}
