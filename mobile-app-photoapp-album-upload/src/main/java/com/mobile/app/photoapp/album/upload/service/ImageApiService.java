package com.mobile.app.photoapp.album.upload.service;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.app.photoapp.album.upload.dto.ImageApiDto;

public interface ImageApiService {

	public ImageApiDto uploadImage(MultipartFile file);
	
	public ImageApiDto getImage(String imageName);
}
