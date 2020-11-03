package com.mobile.app.photoapp.album.upload.dto;

import java.sql.Blob;

import lombok.Data;

@Data
public class ImageApiDto {

	private String id;
	private String fileName;
	private String path;
	private String url;
	private Blob image;
	
}
