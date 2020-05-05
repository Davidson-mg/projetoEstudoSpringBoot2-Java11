package com.davidsonMarcos.projetoEstudo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
/*@Table (name = "tb_Produto") usamos essa anotação quando um nome de uma classe têm um mesmo nome de alguma palavra reservada do BD. Como estamos dando nomes em pt, não têm necessidade */
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id /*Falando que meu atributo id vai ser minha chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*Falando que meu atributo id vai ser auto Imcrement*/
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name = "Produto_categoria", 
	joinColumns = @JoinColumn (name = "fk_produtoId"), 
	inverseJoinColumns = @JoinColumn (name = "fk_CategoriaId"))/*A anotação @JoinTable me permitir colocar alguns atributos. Nela estou
	criando em "name" uma tabela auxiliar (Aquela que é criada numa relação muitos para muitos no BD). Em joinColumns estou criando uma chave estrangeira da tabela produto,
	e em inverseJoinColumns a chave estrangeira da outra tabela do relacionamento (Categoria). Se eu tivesse criando a tabela auxiliar na classe Categoria, 
	seria ao contrario, "joinColumns" teria "fk_CategoriaId" e inverseJoinColumns teria fk_produtoId. Além disso, na classe Categoria devemos colocar uma referencia para este mapeamento
	também no atributo Set que relaciona as duas classes (tabelas)*/
	
	private Set<Categoria> categorias = new HashSet<>(); /*Neste caso estou usando um set ao inves de um List. Estamos fazendo isso pq o Set representa um conjunto,
	e temos que garantir que não vamos ter um produto com mais de uma ocorrencia da mesma categoria. Estamos instanciando usando HashSet pq o Set é uma interface e não
	pode ser instanciado, semelhamente quando usamos o List e instanciamos com o ArrayList. Instanciamos para garantir que a coleção não inicie nula.Produto têm 
	uma relação muitos para muitos com categoria, então o mesmo foi feito na classe Categoria. Obs: no caso de coleções criamos apenas o metodo get e não o set.
	Além disso, não a inserimos no construtor pois já está sendo instaciado*/
	
	@OneToMany(mappedBy = "id.produto") /*Estou dizendo para o jpa que Produto tem uma relação um para muitos com ProdutoOrdem. mappedBy informa que está 
	mapeado com o atributo id quando relacionmos Ordem com Produto na classe Ordem. Inserimos "id.produto" pq na classe ProdutoOrdem temos o id que por sua vez tem o produto.
	O ".produto" deve se igual ao nome da variavel Produto na classe OrdemItemPk no pacote pkComposta*/
	private Set<ProdutoOrdem> itens = new HashSet<>(); /*A classe (tabela) Produto têm uma relação muitos para muitos com Ordem, por isso, no BD é gerada a tabela 
	auxiliar. Neste caso, a tebela auxiliar é a ProdutoOrdem. Vamos ter que criar o metodo get (getOrdens) criado abaixo*/
	
	public Produto() {}
	
	public Produto(Long id, String nome, String descricao, Double preco, String imgUrl) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imgUrl = imgUrl;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@JsonIgnore /*Essa anotação serve para quando eu executar minha aplicação e chamar um ProdutoOrdem, impedir o cliente (usuario) fique chamando o ProdutoOrdem e o 
	ProdutoOrdem chamando o cliente infinitamente (Loop). Pode ser colocado em qualquer lado da relação. Quando vc têm uma associação muitos para um, se vc carregar um obj 
	do lados do muitos, o obj do lado do um vêm automaticamente, mas isso não acontece se vc carregar um obj do lado do um. Por isso estamos usando o @JsonIgnore do lado do muitos.
	Além disso, neste caso estamos usando o @JsonIgnore no metodo get ao inves do atributo, a exemplo do que foi feito na classe Usuario. Isso pq não temos um atributo
	Ordem diretamente, e sim um atributo id que chama um Produto.*/
	public Set<Ordem> getOrdens (){ /*Este metodo está relacionado a relação muitos pra muitos entre as classes Produto e Ordem. Para cara produto que têm mais 
	de um produtoOrdem (tabela auxiliar), devemos percorrer a coleção de ProdutoOrdem, e para cada ProdutoOrdem devemos pegar a Ordem (pedido) relacionado a ele*/
		
		Set<Ordem> set = new HashSet<>();
		
		for (ProdutoOrdem x : itens) {
			
			set.add(x.getOrdem());
			
		}
		
		return set;
		
	}  
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
