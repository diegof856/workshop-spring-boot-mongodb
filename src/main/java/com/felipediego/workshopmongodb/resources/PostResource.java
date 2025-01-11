package com.felipediego.workshopmongodb.resources;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipediego.workshopmongodb.domain.Post;
import com.felipediego.workshopmongodb.resources.util.URL;
import com.felipediego.workshopmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping(value = "{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);

	}
	
	@GetMapping(value = "titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping(value = "fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		
			Instant min = URL.convertDate(minDate, Instant.EPOCH); 
		    Instant max = URL.convertDate(maxDate, Instant.now()).plus(1, ChronoUnit.DAYS); 
		   
			List<Post> list = postService.fullSearch(text,min,max);
			
			return ResponseEntity.ok().body(list);
		

	}

}
