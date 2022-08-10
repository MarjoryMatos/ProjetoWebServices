package com.ibm.pratica.praticando.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ibm.pratica.praticando.model.Category;
import com.ibm.pratica.praticando.model.Order;
import com.ibm.pratica.praticando.model.OrderItem;
import com.ibm.pratica.praticando.model.Payment;
import com.ibm.pratica.praticando.model.Product;
import com.ibm.pratica.praticando.model.User;
import com.ibm.pratica.praticando.model.enums.OrderStatus;
import com.ibm.pratica.praticando.repository.CategoryRepository;
import com.ibm.pratica.praticando.repository.OrderItemRespository;
import com.ibm.pratica.praticando.repository.PedidoRepository;
import com.ibm.pratica.praticando.repository.ProductRespository;
import com.ibm.pratica.praticando.repository.UserRespository;
/*
  Classe de configuracao especifica para teste
 */


@Configuration
@Profile("test")
//implementando o command para que o run seja executado quando o projeto for rodado
public class TestConfig implements CommandLineRunner{
	
	//realiza a injecao de dependencias
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRespository productRepository;
	
	@Autowired
	private OrderItemRespository orderItemRepository;
	
	/*
	 * Instanciando os objetos e salvando no banco de dados
	 */
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		
		Product p1 = new Product(null, "nome", "description", 123.2, "");
		Product p2 = new Product(null, "nome", "description", 123.2, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		
		productRepository.saveAll(Arrays.asList(p1, p2));
		
		User u1 = new User(null, "Maria", "MariaSocial");
		User u2 = new User(null, "Marcio", "MarcioSocial");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:072"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-20T19:53:080"), OrderStatus.WAITING_PAYMENT, u2);
		
		
		userRespository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(o1, o2));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p1, 2, p1.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(o1, o2));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T18:53:007"), o1);
		o1.setPayment(pay1);
		
		pedidoRepository.save(o1);
		
	}
	
	
}
