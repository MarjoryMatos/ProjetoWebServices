package com.ibm.pratica.praticando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.pratica.praticando.model.OrderItem;
import com.ibm.pratica.praticando.model.User;


/*

A anotation se torna opcional pois o jpa ja informa que
a classe extendeu de um repository.
Repository eh responsavel por fazer operacoes junto ao User

*/


public interface OrderItemRespository extends  JpaRepository<OrderItem, Long>{
	

}
