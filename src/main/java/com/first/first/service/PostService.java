package com.first.first.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.first.bean.Post;
import com.first.first.repo.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getPosts(){
		List<Post> p=this.postRepository.findAll();
		return p;
	}
	
	public Post createPost(Post post) {
		post=this.postRepository.save(post);
		return post;
	}
	
	public Post updatePost(Post post) {
		boolean exist=this.postRepository.existsById(new ObjectId(post.getId()));
		if(exist==true) {
			Post existing=this.postRepository.findById(new ObjectId(post.getId())).get();
			System.out.println(existing.getTitle()+" "+existing.getCreatedAt()+" "+existing.getCreator()+" "+existing.getMessage()+" "+existing.getName()+" "+existing.getSelectedFile());
			post.concat(existing);
			post=this.postRepository.save(post);
			return post;
		}else return null;
	}
	
	public boolean deletePost(String id) {
		boolean exist=this.postRepository.existsById(new ObjectId(id));
		if(exist==true) {
			this.postRepository.deleteById(new ObjectId(id));
			return exist;
		}else return exist;
	}	

}
