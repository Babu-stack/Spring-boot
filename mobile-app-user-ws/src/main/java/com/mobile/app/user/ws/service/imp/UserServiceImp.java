package com.mobile.app.user.ws.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.mobile.app.user.ws.fiegn.client.AlbumClient;
import com.mobile.app.user.ws.model.AlbumResponseModel;
import com.mobile.app.user.ws.repository.UserRepository;
import com.mobile.app.user.ws.service.UserService;
import com.mobile.app.user.ws.shared.UserDto;
import com.mobile.app.user.ws.utility.UserEntity;

import feign.FeignException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/*@Autowired
	private RestTemplate restTemplate;*/
	
	@Autowired
	private Environment environment;
	@Autowired
	private AlbumClient albumClient;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		ModelMapper modelMapper =new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userdetails=modelMapper.map(userDto, UserEntity.class);
	
		userRepo.save(userdetails);
		UserDto returnValue=modelMapper.map(userdetails, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=userRepo.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
	
		UserEntity userEntity=userRepo.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userEntity,UserDto.class);
	}

	@Override
	public UserDto getUserById(String userId) {
		 
		UserEntity userEntity  =userRepo.findByUserId(userId);
		if(userEntity==null) throw new UsernameNotFoundException(userId);
		UserDto userDto=new ModelMapper().map(userEntity,UserDto.class);
		
		/*String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
        System.out.println("|"+environment.getProperty("albums.url"));
        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
        });*/
		
		
        List<AlbumResponseModel> albumsList=null;
		
		albumsList = albumClient.getAlbums(userId);
		
		
        userDto.setAlbumsList(albumsList);
		return userDto;
		
	}

	
	
	
}
