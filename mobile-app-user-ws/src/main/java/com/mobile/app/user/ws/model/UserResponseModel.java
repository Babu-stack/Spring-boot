package com.mobile.app.user.ws.model;

import java.util.List;

public class UserResponseModel {
	private String firstName;
	private String lastName;
	private String email;
	private String userId;
	List<AlbumResponseModel> albumsList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AlbumResponseModel> getAlbumsList() {
		return albumsList;
	}

	public void setAlbumsList(List<AlbumResponseModel> albumsList) {
		this.albumsList = albumsList;
	}

}
