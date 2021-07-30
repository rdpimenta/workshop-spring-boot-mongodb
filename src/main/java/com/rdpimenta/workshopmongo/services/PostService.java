package com.rdpimenta.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdpimenta.workshopmongo.domain.Post;
import com.rdpimenta.workshopmongo.repository.PostRepository;
import com.rdpimenta.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	private Integer dayInMilli = 24 * 60 * 60 * 1000;

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + dayInMilli);
		
		return repo.fullSearch(text, minDate, maxDate);
	}
}
