package com.ibm.pratica.praticando.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*
 * Informando que a classe eh uma entidade.
 * Ela quem ira montar as tabelas no banco de dados com as entidades que declaramos.
 * Metodo serializable defini que podemos transformar este dados em cadeias de bytes
 * (para que o objeto trafegue na rede).
*/
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Informa que este atributo eh a chave primaria no banco de dados 
	 * e faz um auto incremento no id, gerando automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpnj;
	private String razaoSocial;
	

	@OneToMany(mappedBy = "usuario")
	private List<Order> pedidos = new ArrayList<>();
	
	//Metodos construtores
	//Metodo construtor vazio
	public User () {
		
	}

	//Metodo construtor recebendo todos os atributos definidos na classe
	public User(Long id, String cpnj, String razaoSocial) {
		super();
		this.id = id;
		this.cpnj = cpnj;
		this.razaoSocial = razaoSocial;
	}

	//Metodos Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public List<Order> getPedidos() {
		return pedidos;
	}
	
	//Metodos hashCode e Equals realiza a comparacao dos objetos
	@Override
	public int hashCode() {
		return Objects.hash(cpnj, id, razaoSocial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cpnj, other.cpnj) && Objects.equals(id, other.id)
				&& Objects.equals(razaoSocial, other.razaoSocial);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

	
	
	
	
	
	
}
