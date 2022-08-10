package com.ibm.pratica.praticando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.pratica.praticando.model.Order;

public interface PedidoRepository extends JpaRepository<Order, Long>{

}
