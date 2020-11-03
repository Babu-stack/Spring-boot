package com.mobile.app.photoapp.album.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.app.photoapp.album.upload.utility.ImageApiEntity;

public interface ImageApi extends JpaRepository<ImageApiEntity, Long> {

	ImageApiEntity findById(String Id);
	
}
