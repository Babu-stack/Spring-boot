package com.mobile.app.photoapp.album.controller;



import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Type;

import com.mobile.app.photoapp.album.Entity.AlbumEntity;
import com.mobile.app.photoapp.album.model.AlbumResponseModel;
import com.mobile.app.photoapp.album.service.PhotoAppService;

@RestController
@RequestMapping("users/{id}/albums")
public class PhotoAlbumController {

	@Autowired
	private PhotoAppService service;
	
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE
	})
	public List<AlbumResponseModel> getAlbums(@PathVariable String id){
		
		List<AlbumResponseModel> returnValue=new ArrayList<>();
		
		List<AlbumEntity> albumEntities =service.getAlbum(id);
		
		if(albumEntities == null || albumEntities.isEmpty()) {
			
			return null;
		}
		Type listType =new TypeToken<List<AlbumResponseModel>>() {}.getType();
		
		returnValue=new ModelMapper().map(albumEntities, listType);
		
		return returnValue;
	}
	
	
}
