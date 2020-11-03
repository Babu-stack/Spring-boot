package com.mobile.app.user.ws.feign.decoder;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		switch(response.status()) {
		case 400:
			break;
		case 404:{
			
			if(methodKey.contains("getAlbums")) {
				
				return  new ResponseStatusException(HttpStatus.valueOf(response.status()),"Albums Microservice not avilable");
			}
			break;
		}
		default:
			return new Exception(response.reason());
		
		
		}
		
		
		return null;
	}

}
