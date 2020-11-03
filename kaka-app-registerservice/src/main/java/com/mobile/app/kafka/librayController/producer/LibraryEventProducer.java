package com.mobile.app.kafka.librayController.producer;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LibraryEventProducer {

	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	String topic="library-event";
	
	public ListenableFuture<SendResult<Integer, String>> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
		Integer key=libraryEvent.getLibraryEventId();
		String value =objectMapper.writeValueAsString(libraryEvent);
	
	ListenableFuture<SendResult<Integer, String>> listenableFuture=	kafkaTemplate.sendDefault(key,value);
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				// TODO Auto-generated method stub
				handleSuccess(key,value,result);
				
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				handlefailure(key, value, ex);
				
			}
			
		});
		return listenableFuture;
	}

	public void sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException, InterruptedException, ExecutionException {
		Integer key=libraryEvent.getLibraryEventId();
		String value =objectMapper.writeValueAsString(libraryEvent);
		log.info("inside sync ");
		SendResult<Integer,String> sendResult=kafkaTemplate.sendDefault(key,value).get();
		//return sendResult;
		
	}
	
	public ListenableFuture<SendResult<Integer, String>> sendLibraryEvent_approach2(LibraryEvent libraryEvent) throws JsonProcessingException {
		Integer key=libraryEvent.getLibraryEventId();
		String value =objectMapper.writeValueAsString(libraryEvent);
		ProducerRecord<Integer,String> producerRecord=handleProducerRecord(topic,key,value);
		ListenableFuture<SendResult<Integer,String>> listenableFuture= kafkaTemplate.send(producerRecord);
	
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {
				// TODO Auto-generated method stub
				handleSuccess(key,value,result);
				
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				handlefailure(key, value, ex);
				
			}
			
		});
		return listenableFuture;
		}
	
	private ProducerRecord<Integer, String> handleProducerRecord(String topic2, Integer key, String value) {
		// TODO Auto-generated method stub
		//List<Header> requestHeader=List<Header>(new RecordHeader("event-source", "scanner".getBytes()));
		return new ProducerRecord<Integer, String>(topic2,key, value);
	}

	protected void handlefailure(Integer key, String value, Throwable ex) {
		// TODO Auto-generated method stub
		log.error("Error sending msg "+ex.getMessage());
		try {
		throw ex;
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error(e.getLocalizedMessage());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
		// TODO Auto-generated method stub
		log.info("msg sent successful or the key :"+key+" and value :"+value+" partision is :"+result.getRecordMetadata().partition());
	}
}
