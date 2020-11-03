package com.mobile.app.user.ws.fiegn.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mobile.app.user.ws.model.AlbumResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name="albums-ws",fallbackFactory =AlbumClientFallbackFactory.class)
public interface AlbumClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable String id );
		
	
}

@Component
class AlbumClientFallbackFactory implements FallbackFactory<AlbumClient> {

	@Override
	public AlbumClient create(Throwable cause) {
		System.out.println("Fall Back");
		return new AlbumClientFallback(cause);
	}
	

}

class AlbumClientFallback implements AlbumClient{

	Logger logger =LoggerFactory.getLogger(this.getClass());
	private final Throwable cause;
	public AlbumClientFallback(Throwable cause) {
		this.cause = cause;
	}
	
	@Override
	public List<AlbumResponseModel> getAlbums(String id) {
		
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getAlbums was called with userId: " + id + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}
		
		return new ArrayList<>();
	}

	
}



