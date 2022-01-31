package com.first.first.bean;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "postmessages")
public class Post implements Serializable {
	
	private String title,message,name,creator,selectedFile,createdAt;
	
	@Id
	private String id;
//	private ArrayList<String>likes,tags;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Post() {
		
	}
	
	public Post(String title, String message, String name, String creator, String selectedFile, String createdAt
//			ArrayList<String> likes, ArrayList<String> tags
			) {
		super();
		this.title = title;
		this.message = message;
		this.name = name;
		this.creator = creator;
		this.selectedFile = selectedFile;
		this.createdAt = createdAt;
//		this.likes = likes;
//		this.tags = tags;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getSelectedFile() {
		return selectedFile;
	}
	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
//	public ArrayList<String> getLikes() {
//		return likes;
//	}
//	public void setLikes(ArrayList<String> likes) {
//		this.likes = likes;
//	}
//	public ArrayList<String> getTags() {
//		return tags;
//	}
//	public void setTags(ArrayList<String> tags) {
//		this.tags = tags;
//	}
	public void concat(Post other) {
		if(this.getId()==null) {
			this.setId(other.getId());
		}
		
		if(this.getTitle()==null) {
			this.setTitle(other.getTitle());
		}
		
		if(this.getCreator()==null) {
			this.setCreator(other.getCreator());
		}
		
		if(this.getMessage()==null) {
			this.setMessage(other.getMessage());
		}
		
		if(this.getSelectedFile()==null) {
			this.setSelectedFile(other.getSelectedFile());
		}
		
		if(this.getCreator()==null) {
			this.setCreator(other.getCreator());
		}
		
		if(this.getCreatedAt()==null) {
			this.setCreatedAt(other.getCreatedAt());
		}
	}
}
