package com.mobile.app.user.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import com.mobile.app.user.ws.utility.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String UserId);
}
