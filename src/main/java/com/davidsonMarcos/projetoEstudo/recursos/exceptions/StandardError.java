package com.davidsonMarcos.projetoEstudo.recursos.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable { /*Quando chamamos um obj que não existe, é retornado por padrão uma resposta contendo os atributos abaixo. Essa classe têm o obj 
de personalizar essa resposta. Deve ser criada no pacote "recursos" pois quem trabalha com resposta e requisição é o pacote "recursos". A classe ResourceExceptionHandler vai tratar 
as exceções. Também criamos no pacote "sevicos.exceptions" uma classe de exceção personalizada que trata o erro 500 quando um obj não existe. */

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") /*Garantindo que o meu instant seja mostrado no Json no formato de string do ISO 8601*/
	private Instant timestamp;
	private Integer status;
	private String error;
	private String messege;
	private String path;
	
	public StandardError() {}
	
	public StandardError(Instant timestamp, Integer status, String error, String messege, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.messege = messege;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
