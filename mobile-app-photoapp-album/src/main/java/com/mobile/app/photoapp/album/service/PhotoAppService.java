package com.mobile.app.photoapp.album.service;



import java.util.List;

import com.mobile.app.photoapp.album.Entity.AlbumEntity;

public interface PhotoAppService {

	public List<AlbumEntity> getAlbum(String userId);
	
}
