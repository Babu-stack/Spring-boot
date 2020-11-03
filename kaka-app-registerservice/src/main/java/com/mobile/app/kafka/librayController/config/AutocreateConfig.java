package com.mobile.app.kafka.librayController.config;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutocreateConfig {

	@Bean
	public NewTopic librarytopic(){
		return TopicBuilder.name("library-event")
		.partitions(3)
		.replicas(3)
		.build();
	}
	
	
}
