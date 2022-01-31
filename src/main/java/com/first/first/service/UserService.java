package com.first.first.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.first.first.bean.User;
import com.first.first.exception.ResourceNotFoundException;
import com.first.first.repo.UserRepository;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User signup(User user) {
//		System.out.println(user);
		User existingUser = this.userRepository.findByEmail(user.getEmail());
//		System.out.println(existingUser.getName());
		if(existingUser!=null) return null;
//		System.out.println("null after");
		int strength = 12; // work factor of bcrypt
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		this.userRepository.save(user);			
		return user;
	}
	
	public User signin(User user) throws ResourceNotFoundException {
		User existingUser = this.userRepository.findByEmail(user.getEmail());
		if(existingUser==null) throw new ResourceNotFoundException("User not found with this email : "+user.getEmail());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());
		Boolean res=bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword());
		if(res==false)
			return null;
		return existingUser;
	}
	
}
