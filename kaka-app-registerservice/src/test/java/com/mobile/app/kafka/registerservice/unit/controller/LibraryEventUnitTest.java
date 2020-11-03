package com.mobile.app.kafka.registerservice.unit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.app.kafka.librayController.LibraryEventController;
import com.mobile.app.kafka.librayController.libraydomain.Book;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEvent;
import com.mobile.app.kafka.librayController.producer.LibraryEventProducer;

@WebMvcTest(LibraryEventController.class)
@AutoConfigureMockMvc
public class LibraryEventUnitTest {

	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	LibraryEventProducer libraryEventProducer;
	
	@Test
	void postLibraryEvent() throws Exception {
		
		Book book = Book.builder()
				.bookId(112)
				.bookAuthor("vj")
				.bookName("ff")
				.build();
		LibraryEvent libraryEvent=LibraryEvent.builder()
				.libraryEventId(null)
				.book(book)
				.build();
		
		String json=objectMapper.writeValueAsString(libraryEvent);
		doNothing().when(libraryEventProducer).sendLibraryEventSync(libraryEvent);;
		mockMvc.perform(post("/v1/libraryevent")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
	}
	
	

}
