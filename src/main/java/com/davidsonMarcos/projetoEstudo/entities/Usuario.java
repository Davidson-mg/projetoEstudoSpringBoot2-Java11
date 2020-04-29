package com.davidsonMarcos.projetoEstudo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore; /*Sempre importar o javax.persistence.Entity e não o org.hibernate.annotarion.Enity. Estamos fazendo 
a classe depender da especificação e não da implementação.*/

@Entity
public class Usuario implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id /*Falando que meu atributo id vai ser minha chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*Falando que meu atributo id vai ser auto Imcrement*/
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String password;
	
	@JsonIgnore /*Essa anotação serve para quando eu executar minha aplicação e chamar um pedido, impedir o cliente (usuario) fique chamando o servico (Ordem) e o servico chamando o cliente infinitamente (Loop). Pode ser colocado em qualquer lado da relação
	Quando vc têm uma associação muitos para um, se vc carregar um obj do lados do muitos, o obj do lado do um vêm automaticamente, mas isso não acontece se vc carregar um obj do lado do um. Por isso estamos usando o @JsonIgnore do lado do muitos*/
	@OneToMany (mappedBy = "cliente")/*Estou dizendo para o jpa que o Cliente (Usuario) tem uma relação um para muitos com servico (Ordem). mappedBy informa que está mapeado com o atributo cliente quando relacionmos Ordem com Usuario na classe Ordem*/
	private List<Ordem> ordens = new ArrayList<>(); /*Relacionando o meu usuario com o servico (ordem). Um usuario pode ter varios servicos,
	por isso usamos um list. A lista de pedidos do usuario é uma coleção, por isso coloquei "= new ArrayList<>()". Temos também que acrescentar 
	o metodo get. Como é uma coleção, não implementamos o metodo set*/
	
	
	
	public Usuario () {}

	public Usuario(Long id, String nome, String email, String telefone, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Ordem> getOrdens() {
		return ordens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
}
