package com.davidsonMarcos.projetoEstudo.entities.pkComposta;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.davidsonMarcos.projetoEstudo.entities.Ordem;
import com.davidsonMarcos.projetoEstudo.entities.Produto;

@Embeddable /*Usamos essa anotação para dizer que é uma classe auxiliar de chave primaria composta*/
public class OrdemItemPk implements Serializable {
	
	/*Neste exemplo de projeto, na relação entre as classes (tabelas) Produto e Ordem (serviço), devemos criar uma classe (tabela) auxiliar referente a tabela
	 auxiliar que é criada numa relação muitos para muitos no BD. E nessa classe (tabela) auxiliar, se tratando de banco de dodos, devemos inserir uma chave composta 
	 pelas chaves estrangeiras das tabelas Produto e Ordem. Todavia, se tratando de Java, não podemos inserir essa chave composta na mesma tabela auxiliar que
	 é gerada no ralacionamento muitos para muitos. Por este motivo, devemos criar uma segunda classe auxiliar que vai armazenar somente a chave composta.
	 Dessa forma, uma unica tabela auxiliar do banco de dados seria representada por duas classes auxiliares no java, uma com os atributos da classe e outra com
	 a chave composta. Essa em que estamos é referente a chave composta e a classe ProdutoOrdem no pacote entities é referente os atributos*/
	
	
	/*Não precisa criar construtor neste caso*/
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne /*Estou dizendo para o jpa que a classe OrdemItemPk tem uma relação muitos para um com Ordem (pedido)*/
	@JoinColumn(name = "fk_ordemId") /*Estou criando uma chave estrangeira da classe Ordem dentro da classe OrdemItemPk*/
	private Ordem ordem;
	
	@ManyToOne /*Estou dizendo para o jpa que a classe OrdemItemPk tem uma relação muitos para um com Produto*/
	@JoinColumn(name = "fk_produtoId") /*Estou criando uma chave estrangeira da classe Produto dentro da classe OrdemItemPk*/
	private Produto produto;
	
	public Ordem getOrdem() {
		return ordem;
	}
	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Override
	public int hashCode() { /*Neste caso vamos usar os dois atributos para comparar*/
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		OrdemItemPk other = (OrdemItemPk) obj;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
	
}
