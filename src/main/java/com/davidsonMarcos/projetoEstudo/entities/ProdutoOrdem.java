package com.davidsonMarcos.projetoEstudo.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.davidsonMarcos.projetoEstudo.entities.pkComposta.OrdemItemPk;

@Entity
public class ProdutoOrdem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*Neste exemplo de projeto, na relação entre as classes (tabelas) Produto e Ordem (serviço), devemos criar uma classe (tabela) auxiliar referente a tabela
	 auxiliar que é criada numa relação muitos para muitos no BD. E nessa classe (tabela) auxiliar, se tratando de banco de dodos, devemos inserir uma chave composta 
	 pelas chaves estrangeiras das tabelas Produto e Ordem. Todavia, se tratando de Java, não podemos inserir essa chave composta na mesma tabela (classe) auxiliar que
	 é gerada no ralacionamento muitos para muitos. Por este motivo, devemos criar uma segunda classe auxiliar que vai armazenar somente a chave composta.
	 Dessa forma, uma unica tabela auxiliar do banco de dados seria representada por duas classes auxiliares no java, uma com os atributos da classe e outra com
	 a chave composta. Essa em que estamos é referente os atributos, e a classe OrdemStatusPk no pacote pkComposta é referente a chave composta */
	
	@EmbeddedId /*Essa anotação é semelhante a anotação "@Id" para dizer para o BD que esse atributo é um Id. Só que por se tratar de uma chave composta estamos usando "@EmbeddedId"*/
	private OrdemItemPk id = new OrdemItemPk(); /*Sempre que vc criar uma classe auxiliar para id composto, vc deve instanciar*/
	
	private Integer quantidade;
	private Double preco;
	
	public ProdutoOrdem () {}

	public ProdutoOrdem(Ordem ordem, Produto produto, Integer quantidade, Double preco) { /*Repare que no construtor e nos gets e sets não inserimos o atributo id do 
	tipo OrdemItemPk e inserimos os atributos como paramentro referentes as duas classes que estão se relacionando de forma muitos para muitos.*/
		super();
		id.setOrdem(ordem); /*Passando para o Id as duas classe que estão se relacionando*/
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Ordem getOrdem () { /*Apesar de não termos diretamente os atributos Ordem e Produto acima, devemos criar os metodos get e set para ambos. Isso pq para o que
	é externo a classe, a minha classe ProdutoOrdem não vai retornar um getId com a chave composta pelas duas classes que estão se relacionadndo (Produto e Ordem).
	Ela deve retornar Ordem e Produto separadamente, um de cada vez */
		
		return id.getOrdem();
		
	}
	
	public void setOrdem (Ordem ordem) {
		
		id.setOrdem(ordem);
		
	}
	
	public Produto getProduto () { 
		
		return id.getProduto();
		
	}
	
	public void setProduto (Produto produto) {
		
		id.setProduto(produto);
		
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ProdutoOrdem other = (ProdutoOrdem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
