package com.felipediego.workshopmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.felipediego.workshopmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
