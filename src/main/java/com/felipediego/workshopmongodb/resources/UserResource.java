package com.felipediego.workshopmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipediego.workshopmongodb.domain.User;

@RestController
@RequestMapping(value= "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User diego = new User("1", "Diego felipe", "diego@gmail.com");
		User jesus = new User("2", "jesus nazare", "jesus@vaticano.com");
		
		List<User> list = new ArrayList<User>();
		list.addAll(Arrays.asList(diego, jesus));
		return ResponseEntity.ok().body(list);
		
	}
}
