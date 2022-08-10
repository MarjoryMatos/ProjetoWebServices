package com.ibm.pratica.praticando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.pratica.praticando.model.Product;
import com.ibm.pratica.praticando.services.ProductService;


/*controlador que faz o meio de campo entre a aplicacao e as regras de negocio
o controlador ira depender do servico e o servico do repository */

//value define o nome dos endpoints
@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	//Esta injecao realiza a injecao de dependencias
	@Autowired
	private ProductService service;
	
	
	//Este metodo busca todos os usuarios
	//ResponseEntity retorna respostas de requisicoes web
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//Este metodo busca todos os usuarios por id
	/*o value informa que iremos passar o id dentro da url 
	e para receber como parametro adiciamos o Path */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
