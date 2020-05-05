package com.davidsonMarcos.projetoEstudo.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id /*Falando que meu atributo id vai ser minha chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*Falando que meu atributo id vai ser auto Imcrement*/
	private Long id;
	private Instant momento;
	
	/*A classe (tabela) Pagamento têm uma relação uma para um com a classe Ordem (pedido), onde a classe Pagamento é dependente e a classe Ordem é independente.
	Isso pq nós podemos ter um padido (ordem) sem nenhum pagamento*/
	
	@OneToOne /*Dizendo que a classe Ordem têm relação um para um com Pagamento*/
	@MapsId /*É necessario colocar essa anotação no atributo de associação na classe dependente*/
	private Ordem ordem;
	
	public Pagamento () {}

	public Pagamento(Long id, Instant momento, Ordem ordem) {
		super();
		this.id = id;
		this.momento = momento;
		this.ordem = ordem;
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

	public Ordem getOrdem() {
		return ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
