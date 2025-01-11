package com.felipediego.workshopmongodb.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipediego.workshopmongodb.domain.Post;
import com.felipediego.workshopmongodb.repositories.PostRepository;
import com.felipediego.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!"));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.searchTitle(text);
	}
	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
	
		return postRepository.fullSearch(text, minDate, maxDate);
	}

}
