package com.mobile.app.kafka.librayController;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobile.app.kafka.librayController.libraydomain.Book;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEvent;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEventType;
import com.mobile.app.kafka.librayController.producer.LibraryEventProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LibraryEventController {

	@Autowired
	LibraryEventProducer libEventProducer;
	@PostMapping(value = "/v1/libraryevent",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LibraryEvent> postLibraryEvents(@RequestBody @Valid LibraryEvent libraryEvent) throws  InterruptedException, ExecutionException, JsonProcessingException{
	
		log.info("before library-event");
		//libEventProducer.sendLibraryEvent(libraryEvent);
		libraryEvent.setLibraryEventType(LibraryEventType.NEW);
		libEventProducer.sendLibraryEventSync(libraryEvent);
		//libEventProducer.sendLibraryEvent_approach2(libraryEvent);
		log.info("after library-event");
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
	}
	
	
	@PutMapping(value = "/v1/libraryevent",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> putLibraryEvents(@RequestBody @Valid LibraryEvent libraryEvent) throws  InterruptedException, ExecutionException, JsonProcessingException{
	
		if(libraryEvent.getLibraryEventId()==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID CANT BE NULL");
		}
		log.info("before library-event");
		//libEventProducer.sendLibraryEvent(libraryEvent);
		libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
		libEventProducer.sendLibraryEvent_approach2(libraryEvent);
		//libEventProducer.sendLibraryEvent_approach2(libraryEvent);
		log.info("after library-event");
		return ResponseEntity.status(HttpStatus.OK).body(libraryEvent);
	}
	
	@GetMapping(value = "/v1/get",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getbook(@RequestBody Book book){
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}
	@GetMapping("/status/check")
	public String getUser() {
		System.out.println("inside status check");
		return "users working on port 8087";
	}
}
