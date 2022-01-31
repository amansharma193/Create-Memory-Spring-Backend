package com.first.first.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;


public class UserResponse implements Serializable {
	
	private User user;
	private String token;
	
	public UserResponse() {}
	
	public UserResponse(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
}
