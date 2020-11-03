package com.mobile.app.photoapp.album.upload.serviceImp;

import java.sql.Blob;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.app.photoapp.album.upload.dto.ImageApiDto;
import com.mobile.app.photoapp.album.upload.repository.ImageApi;
import com.mobile.app.photoapp.album.upload.service.ImageApiService;
import com.mobile.app.photoapp.album.upload.utility.ImageApiEntity;

public class ImageApiServiceImp implements ImageApiService {

	@Autowired
	private ImageApi imageApiRepository;
	@Autowired	
	private Environment env;
	
	@Override
	public ImageApiDto uploadImage(MultipartFile file) {
	
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Random r =new Random();
		
		imageApiRepository.save(new ImageApiEntity(r.nextLong(),file.getOriginalFilename(),env.getProperty("upload.path"),"uri",Compress(file.getBytes())));
		String contentType=file.getContentType();
		String fileName=file.getOriginalFilename();
		
	
		
		return null;
	}

	private Blob Compress(byte[] bytes) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageApiDto getImage(String imageName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
