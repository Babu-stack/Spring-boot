package com.appdeveloperblog.app.ws.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> getAllException(Exception ex,
			WebRequest s){
		
		return new ResponseEntity<> (ex,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
