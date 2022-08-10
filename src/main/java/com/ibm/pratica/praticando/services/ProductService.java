package com.ibm.pratica.praticando.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.pratica.praticando.model.Product;
import com.ibm.pratica.praticando.repository.ProductRespository;

//Implementacao das regras de negocio
//Verificara com o repository o que esta sendo requisitado

//registra um servico na camada de servico
@Service
public class ProductService {
	
	//realiza a injecao de dependencias
	@Autowired
	private ProductRespository repository;
	
	//metodo que busca todos os usuarios
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	//metodo que busca todos os usuarios por id
	public Product findById(Long id){
		 Optional<Product> obj = repository.findById(id);
		 return obj.get();
	}

	
	
	

}
