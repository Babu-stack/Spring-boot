package com.mobile.app.kafka.registerservice.intg.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import com.mobile.app.kafka.librayController.libraydomain.Book;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEvent;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"library-event"} ,partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.admin.properties.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
public class LibrarayEventControllerIngTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	void postLibraryevent() {
	
		Book book = Book.builder()
				.bookId(112)
				.bookAuthor("vj")
				.bookName("ff")
				.build();
		LibraryEvent libraryEvent=LibraryEvent.builder()
				.libraryEventId(null)
				.book(book)
				.build();
		HttpHeaders request=new  HttpHeaders();
		request.set("contetnt-type",MediaType.APPLICATION_JSON_VALUE.toString());
		
		HttpEntity< LibraryEvent> entity =new HttpEntity<LibraryEvent>(libraryEvent,request);
		
		ResponseEntity<LibraryEvent> responseEntity=restTemplate.exchange("/v1/libraryevent", HttpMethod.POST,entity,LibraryEvent.class);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
	}

	@Test
	void putLibraryevent() {
	
		Book book = Book.builder()
				.bookId(112)
				.bookAuthor("vj")
				.bookName("ff")
				.build();
		LibraryEvent libraryEvent=LibraryEvent.builder()
				.libraryEventId(156)
				.book(book)
				.build();
		HttpHeaders request=new  HttpHeaders();
		request.set("contetnt-type",MediaType.APPLICATION_JSON_VALUE.toString());
		
		HttpEntity< LibraryEvent> entity =new HttpEntity<LibraryEvent>(libraryEvent,request);
		
		ResponseEntity<LibraryEvent> responseEntity=restTemplate.exchange("/v1/libraryevent", HttpMethod.PUT,entity,LibraryEvent.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
	}
}
