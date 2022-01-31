package com.first.first.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first.bean.Post;
import com.first.first.exception.ResourceNotFoundException;
import com.first.first.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		return new ResponseEntity<String>("this is posts home",HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> getPosts(){
		List<Post> res=postService.getPosts();
		return new ResponseEntity<List<Post>>(res,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post){
		Post res=postService.createPost(post);
		return new ResponseEntity<Post>(res,HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") String id,@RequestBody Post post)throws ResourceNotFoundException {
//		System.out.println(id+" "+post.getTitle()+" "+post.getCreatedAt()+" "+post.getCreator()+" "+post.getMessage()+" "+post.getName()+" "+post.getSelectedFile());
		post.setId(id);
		Post res=postService.updatePost(post);
		if(res!=null) return new ResponseEntity<Post>(res,HttpStatus.OK);
		else throw new ResourceNotFoundException("Post not found with id "+id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") String id) throws ResourceNotFoundException {
		boolean result=postService.deletePost(id);
		if(result==true) {
			return new ResponseEntity<String>("Post deletion successful for id : "+id,HttpStatus.OK);
		}else throw new ResourceNotFoundException("Post not found with id "+id); 
	}
	
}
