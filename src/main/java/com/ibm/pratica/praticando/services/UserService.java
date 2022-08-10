package com.ibm.pratica.praticando.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ibm.pratica.praticando.model.User;
import com.ibm.pratica.praticando.repository.UserRespository;
import com.ibm.pratica.praticando.services.exception.DatabaseException;
import com.ibm.pratica.praticando.services.exception.ResourceNotFoundException;

//Implementacao das regras de negocio
//Verificara com o repository o que esta sendo requisitado

//registra um servico na camada de servico
@Service
public class UserService {
	
	//realiza a injecao de dependencias
	@Autowired
	private UserRespository repository;
	
	//metodo que busca todos os usuarios
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//metodo que busca todos os usuarios por id
	public User findById(Long id){
		 Optional<User> obj = repository.findById(id);
		 return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//metodo para salvar um usuario no banco de dados
	public User insert(User obj) {
		return repository.save(obj);
	}

	
	//delecao do usuario
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/*metodo getOne ira instancia um usuario mas nao ira salva-lo no banco de dados
	 * para poder manipula-lo
	*/
	public User update(Long id, User obj) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	//ira atualizar os dados no entity, conforme o obj
	private void updateData(User entity, User obj) {
		entity.setCpnj(obj.getCpnj());
		entity.setRazaoSocial(obj.getRazaoSocial());
	}

}
