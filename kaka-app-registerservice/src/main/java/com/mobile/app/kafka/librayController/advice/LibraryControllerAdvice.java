package com.mobile.app.kafka.librayController.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class LibraryControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBodyException(MethodArgumentNotValidException ex)   {
		
		List<FieldError> errorList=ex.getBindingResult().getFieldErrors();
		String errorMsg=errorList.stream()
		.map(fieldError->fieldError.getField()+"-"+fieldError.getDefaultMessage())
		.sorted()
		.collect(Collectors.joining(","));
		log.info(errorMsg);
		
		
		return new ResponseEntity<>(errorMsg,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> handleValidation(JsonParseException ex){
		
		ex.getLocation().getSourceRef();
		
		return new ResponseEntity<>(ex.getLocation().getSourceRef(),HttpStatus.BAD_REQUEST);
		
	}
}
