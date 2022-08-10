package com.ibm.pratica.praticando.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.pratica.praticando.model.User;
import com.ibm.pratica.praticando.services.UserService;


/*controlador que faz o meio de campo entre a aplicacao e as regras de negocio
o controlador ira depender do servico e o servico do repository */

//value define o nome dos endpoints
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	//Esta injecao realiza a injecao de dependencias
	@Autowired
	private UserService service;
	
	
	//Este metodo busca todos os usuarios
	//ResponseEntity retorna respostas de requisicoes web
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//Este metodo busca todos os usuarios por id
	/*o value informa que iremos passar o id dentro da url 
	e para receber como parametro adiciamos o Path
	O id serve para recuperarmos ele no banco de dados */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*endopoint para inserir o usuario
	  */
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@PutMapping(value = "{/id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
