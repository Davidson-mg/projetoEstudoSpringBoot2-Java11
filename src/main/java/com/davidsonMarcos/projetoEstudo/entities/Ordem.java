package com.davidsonMarcos.projetoEstudo.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
public class Ordem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id /*Falando que meu atributo id vai ser minha chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*Falando que meu atributo id vai ser auto Imcrement*/
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") /*Garantindo que o meu instant seja mostrado no Json no formato de string do ISO 8601*/
	private Instant momento;
	
	@ManyToOne /*Estou dizendo para o jpa que o usuario tem uma relação muitos para um com cliente (Usuario)*/
	@JoinColumn(name = "fk_clienteId") /*Estou criando uma chave estrangeira da classe Usuario dentro da classe Ordem*/
	private Usuario cliente; /*Relacionado meu servico (Ordem) com meu cliente (Usuario). Um servico pode ter apenas um usuario, por isso
	não usamos uma lista.*/
	
	public Ordem () {}
	
	public Ordem(Long id, Instant momento, Usuario cliente) {
		super();
		this.id = id;
		this.momento = momento;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
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
		Ordem other = (Ordem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
