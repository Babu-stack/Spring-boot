package com.mobile.app.kafka.consumer.listner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class LibraryEventConsumerListner {

	@KafkaListener(topics = {"library-event"})
	public void OnMessage(ConsumerRecord<Integer, String> consumerRecord) {
		
		
		log.info("consumerRecord : {}",consumerRecord);
		
		
	}
	
}
