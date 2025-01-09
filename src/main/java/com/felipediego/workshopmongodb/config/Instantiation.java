package com.felipediego.workshopmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.felipediego.workshopmongodb.domain.User;
import com.felipediego.workshopmongodb.repositories.UserRepository;
@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		User diego = new User(null, "diego felipe", "diego@gmail.com");
		User neymar  = new User(null, "neymar junior", "nj@gmail.com");
		User messi = new User(null, "lionel messi", "messi@hotmail.com");
		
		userRepository.saveAll(Arrays.asList(diego,neymar,messi));
	}

}
