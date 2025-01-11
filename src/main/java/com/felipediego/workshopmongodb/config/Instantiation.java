package com.felipediego.workshopmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.felipediego.workshopmongodb.domain.Post;
import com.felipediego.workshopmongodb.domain.User;
import com.felipediego.workshopmongodb.dto.AuthorDTO;
import com.felipediego.workshopmongodb.dto.CommentDTO;
import com.felipediego.workshopmongodb.repositories.PostRepository;
import com.felipediego.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		User diego = new User(null, "diego felipe", "diego@gmail.com");
		User neymar = new User(null, "neymar junior", "nj@gmail.com");
		User messi = new User(null, "lionel messi", "messi@hotmail.com");

		userRepository.saveAll(Arrays.asList(diego, neymar, messi));

		Post post1 = new Post(null, Instant.parse("2025-01-01T10:00:00Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(diego));
		Post post2 = new Post(null, Instant.parse("2025-01-08T14:00:00Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(diego));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2025-01-05T12:00:00Z"), new AuthorDTO(neymar));
		CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2025-01-07T09:00:00Z"), new AuthorDTO(messi));
		CommentDTO c3 = new CommentDTO( "Bom dia", Instant.parse("2025-01-02T09:00:00Z"), new AuthorDTO(neymar));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		diego.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(diego);
	}

}
