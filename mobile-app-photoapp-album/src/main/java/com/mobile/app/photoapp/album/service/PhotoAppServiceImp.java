package com.mobile.app.photoapp.album.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mobile.app.photoapp.album.Entity.AlbumEntity;

@Service
public class PhotoAppServiceImp implements PhotoAppService {

	@Override
	public List<AlbumEntity> getAlbum(String userId) {
		
		List<AlbumEntity> returnValue =new ArrayList<>();
		
		AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setUserId(userId);
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId(1L);
        albumEntity.setName("album 1 name");
        
        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId(2L);
        albumEntity2.setName("album 2 name");
        
        returnValue.add(albumEntity);
        returnValue.add(albumEntity2);
		
		
		
		return returnValue;
	}

}
