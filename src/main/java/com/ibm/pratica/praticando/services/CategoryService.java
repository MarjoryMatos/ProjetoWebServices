package com.ibm.pratica.praticando.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ibm.pratica.praticando.model.Category;
import com.ibm.pratica.praticando.model.Order;
import com.ibm.pratica.praticando.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

}
