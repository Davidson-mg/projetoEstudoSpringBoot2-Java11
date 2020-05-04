package com.davidsonMarcos.projetoEstudo.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidsonMarcos.projetoEstudo.entities.Categoria;
import com.davidsonMarcos.projetoEstudo.entities.Ordem;
import com.davidsonMarcos.projetoEstudo.entities.Produto;
import com.davidsonMarcos.projetoEstudo.entities.ProdutoOrdem;
import com.davidsonMarcos.projetoEstudo.entities.Usuario;
import com.davidsonMarcos.projetoEstudo.entities.enums.OrdemStatus;
import com.davidsonMarcos.projetoEstudo.repositorios.CategoriaRepositorio;
import com.davidsonMarcos.projetoEstudo.repositorios.OrdemRepositorio;
import com.davidsonMarcos.projetoEstudo.repositorios.ProdutoOrdemRepositorio;
import com.davidsonMarcos.projetoEstudo.repositorios.ProdutoRepositorio;
import com.davidsonMarcos.projetoEstudo.repositorios.UsuarioRepositorio;

@Configuration /*Classe de configuração é uma classe auxiliar que vai fazer algumas configurações na aplicação. É preciso informar com o @Configuration*/
@Profile("test") /*Vai rodar essa configuração somente quando estivermos no perfil de teste*/
public class TesteConfig implements CommandLineRunner { 

	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private OrdemRepositorio ordemRepositorio;
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	private ProdutoOrdemRepositorio produtoOrdemRepositorio;

	@Override
	public void run(String... args) throws Exception { /*Tudo que estiver neste metodo vai ser executado quando a aplicação for executada.
	É um metodo da interface CommandLineRunner. Instanciamos e salvamos no BD nossos objetos dentro deste metodo.*/
		
		Categoria cat1 = new Categoria(null, "Eletronicos"); 
		Categoria cat2 = new Categoria(null, "Livros"); 
		Categoria cat3 = new Categoria(null, "Computadores");
		
		Produto p1 = new Produto(null, "O Senhor dos aneis", "Frodo precisa destruir o anel.", 90.5, ""); 
		Produto p2 = new Produto(null, "Smart TV", "Tv com alta resolução. Não assista BBB pois é coisa de idiota.", 2190.0, ""); 
		Produto p3 = new Produto(null, "Macbook Pro", "Computador do Steve jobs que não roda jogos e varias aplicações.", 1250.0, "");
		Produto p4 = new Produto(null, "PC Gamer", "PC que roda tudo no maximo mas não roda The last of us, God of war, Uncharted, entre outros.", 6000.0, ""); 
		Produto p5 = new Produto(null, "O minimos que você precisa saber para não ser um idiota", "Coletânea de 193 artigos e ensaios escritos entre 1997 e 2013.", 100.99, ""); 
		
		categoriaRepositorio.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		produtoRepositorio.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategorias().add(cat2); /*Depois de criar a table auxiliar que faz o relacionamento muitos para muitos entre produto e categoria na classe produto, 
		estamos associando o obj p1 que é um produto, ao obj cat2, que é a categoria do produto.*/
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat3);
		p3.getCategorias().add(cat3);
		p4.getCategorias().add(cat3);
		p5.getCategorias().add(cat2);
		
		produtoRepositorio.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); /*salvando novamente só que agora com as associações que realizamos acima*/
		
		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Ordem o1 = new Ordem(null, Instant.parse("2019-06-20T19:53:07Z"), OrdemStatus.PAGO, u1); /*Esse formato de data e hora tá no padrão iso 8601. A letra "Z" no final indica que é UTC*/ 
		Ordem o2 = new Ordem(null, Instant.parse("2019-07-21T03:42:10Z"), OrdemStatus.AGUARDANDO_PAGAMENTO, u2); /*Repare que estamos usando a variavel u2 que é um obj usuario. Estamos instanciando o pedido (Ordem) passando o usuario associado a ele*/
		Ordem o3 = new Ordem(null, Instant.parse("2019-07-22T15:21:22Z"), OrdemStatus.AGUARDANDO_PAGAMENTO, u1); /*Lembrando que o horario do Brasil é 3h atrasado em relação ao UTC. Por isso vai retornar com 3 horas a mais do que o obj*/
		
		usuarioRepositorio.saveAll(Arrays.asList(u1, u2)); /*Salvando nossos objs no banco. Chamando a classe usuarioRepositorio que acessa o bd, a patir dele chamamos o
		metodo saveAll que passa uma lista Arrays.asList e dentro dela os dois objetos que criamos acima*/
		
		ordemRepositorio.saveAll(Arrays.asList(o1, o2, o3));
		
		ProdutoOrdem po1 = new ProdutoOrdem(o1, p1, 2, p1.getPreco()); /*Repare que estamos passando como paramentro um pedido 1 (o1), produto 1 (p1), quantidade (2), e preco estamos reproduzindo o precço do p1*/
		ProdutoOrdem po2 = new ProdutoOrdem(o1, p3, 1, p3.getPreco()); 
		ProdutoOrdem po3 = new ProdutoOrdem(o2, p3, 2, p3.getPreco()); 
		ProdutoOrdem po4 = new ProdutoOrdem(o3, p5, 2, p5.getPreco());
		
		produtoOrdemRepositorio.saveAll(Arrays.asList(po1, po2, po3, po4));
		
	}
	
}
