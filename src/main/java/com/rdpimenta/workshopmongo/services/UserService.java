package com.rdpimenta.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdpimenta.workshopmongo.domain.User;
import com.rdpimenta.workshopmongo.dto.UserDTO;
import com.rdpimenta.workshopmongo.repository.UserRepository;
import com.rdpimenta.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User user = findById(obj.getId());
		updateData(user, obj);
		return repo.save(user);
	}

	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}
}
