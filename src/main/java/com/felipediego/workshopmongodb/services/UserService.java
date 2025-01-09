package com.felipediego.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipediego.workshopmongodb.domain.User;
import com.felipediego.workshopmongodb.dto.UserDTO;
import com.felipediego.workshopmongodb.repositories.UserRepository;
import com.felipediego.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	public User findById(String id) {
		Optional<User> obj  = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!"));
	}
	public User insert(User user) {
		return userRepository.insert(user);
	}
	public void delete(String id) {
		Optional<User> obj = userRepository.findById(id);
		obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!"));
		userRepository.deleteById(id);
	}
	public User update(User user) {
		Optional<User> obj = userRepository.findById(user.getId());
		obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!"));
		upadateData(obj.get(),user);
		return userRepository.save(obj.get());
	}
	private void upadateData(User userDb, User user) {
		userDb.setName(user.getName());
		userDb.setEmail(user.getEmail());
		
	}
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
