package com.mobile.app.kafka.librayController.libraydomain;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryEvent {

	private Integer libraryEventId;
	private LibraryEventType libraryEventType;
	@NotNull
	private Book book;
	
	
	
}
