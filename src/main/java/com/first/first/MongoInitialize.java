package com.first.first;

import java.io.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Service
public class MongoInitialize {

		@PostConstruct
	    public void initialize() {
	        try {
	        	ConnectionString connectionString = new ConnectionString("mongodb+srv://amansharma193:1932815aman@cluster0.wpqkg.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
	        	MongoClientSettings settings = MongoClientSettings.builder()
	        	        .applyConnectionString(connectionString)
	        	        .build();
	        	MongoClient mongoClient = MongoClients.create(settings);
	        	MongoDatabase database = mongoClient.getDatabase("myFirstDatabase");
	        	System.out.println("running...");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
