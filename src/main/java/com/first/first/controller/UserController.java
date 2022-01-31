package com.first.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first.bean.User;
import com.first.first.bean.UserResponse;
import com.first.first.config.JwtTokenUtil;
import com.first.first.exception.ResourceAlreadyExistsException;
import com.first.first.exception.ResourceNotFoundException;
import com.first.first.service.CustomUserDetailsService;
import com.first.first.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("this is user home",HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<UserResponse> signin(@RequestBody User user) throws Exception{
		User result=this.userService.signin(user);
		if(result==null) throw new Exception("Wrong password");
		try {			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(result.getEmail(), result.getPassword())); 
		}catch(Exception e) {
			e.printStackTrace();
		}
//		System.out.println(result.getEmail()+" "+result.getName());
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(result.getEmail());
		String token = jwtTokenUtil.generateToken(userDetails);
		UserResponse userResponse=new UserResponse(result,token);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponse> signup(@RequestBody User user) throws ResourceAlreadyExistsException{
//		System.out.println(user.getName()+" "+user.getEmail()+" "+user.getPassword());
		User result=this.userService.signup(user);
		if(result==null)
			throw new ResourceAlreadyExistsException("User already exists with this email : "+user.getEmail());
		try {			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(result.getEmail(), result.getPassword())); 
		}catch(Exception e) {
			e.printStackTrace();
		}
//		System.out.println(result.getEmail()+" "+result.getName());
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(result.getEmail());
		String token = jwtTokenUtil.generateToken(userDetails);
		UserResponse userResponse=new UserResponse(result,token);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
}
