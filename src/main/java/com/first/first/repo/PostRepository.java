package com.first.first.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.first.first.bean.Post;

public interface PostRepository extends MongoRepository<Post, ObjectId> {

}
