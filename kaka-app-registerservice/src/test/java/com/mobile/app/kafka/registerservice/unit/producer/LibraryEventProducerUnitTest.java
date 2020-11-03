package com.mobile.app.kafka.registerservice.unit.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.app.kafka.librayController.libraydomain.Book;
import com.mobile.app.kafka.librayController.libraydomain.LibraryEvent;
import com.mobile.app.kafka.librayController.producer.LibraryEventProducer;

@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {

	@Mock
	KafkaTemplate<Integer ,String> kafkaTempate;
	
	@Spy
	ObjectMapper objmapper=new ObjectMapper();
	
	@InjectMocks
	LibraryEventProducer eventProducer;
	 
	
	
	
	@Test
	 void SendLibraryEventProducer() throws JsonProcessingException, InterruptedException, ExecutionException {
		Book book = Book.builder()
				.bookId(112)
				.bookAuthor("vj")
				.bookName("ff")
				.build();
		LibraryEvent libraryEvent=LibraryEvent.builder()
				.libraryEventId(null)
				.book(book)
				.build();
	
		SettableListenableFuture future =new SettableListenableFuture<>();
		future.setException(new RuntimeException("Exception in kafka"));
		
		when(kafkaTempate.send(isA(ProducerRecord.class))).thenReturn(future);
		assertThrows(Exception.class,()->eventProducer.sendLibraryEvent_approach2(libraryEvent).get());
	}
}
