package com.first.first.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.first.first.bean.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	@Query("{ 'email' : ?0 }")
	User findByEmail(String email);
	
	@Query("{ 'name' : ?0 }")
	List<User> findByName(String name);
	
}
