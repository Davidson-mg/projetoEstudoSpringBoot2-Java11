package com.davidsonMarcos.projetoEstudo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidsonMarcos.projetoEstudo.entities.Ordem;
import com.davidsonMarcos.projetoEstudo.entities.Usuario;
import com.davidsonMarcos.projetoEstudo.entities.enums.OrdemStatus;
import com.davidsonMarcos.projetoEstudo.repositorios.OrdemRepositorio;
import com.davidsonMarcos.projetoEstudo.repositorios.UsuarioRepositorio;

@Configuration /*Classe de configuração é uma classe auxiliar que vai fazer algumas configurações na aplicação. É preciso informar com o @Configuration*/
@Profile("test") /*Vai rodar essa configuração somente quando estivermos no perfil de teste*/
public class TesteConfig implements CommandLineRunner { 

	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private OrdemRepositorio ordemRepositorio;

	@Override
	public void run(String... args) throws Exception { /*Tudo que estiver neste metodo vai ser executado quando a aplicação for executada.
	É um metodo da interface CommandLineRunner. Instanciamos e salvamos no BD nossos objetos dentro deste metodo.*/
		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Ordem o1 = new Ordem(null, Instant.parse("2019-06-20T19:53:07Z"), OrdemStatus.PAGO, u1); /*Esse formato de data e hora tá no padrão iso 8601. A letra "Z" no final indica que é UTC*/ 
		Ordem o2 = new Ordem(null, Instant.parse("2019-07-21T03:42:10Z"), OrdemStatus.AGUARDANDO_PAGAMENTO, u2); /*Repare que estamos usando a variavel u2 que é um obj usuario. Estamos instanciando o pedido (Ordem) passando o usuario associado a ele*/
		Ordem o3 = new Ordem(null, Instant.parse("2019-07-22T15:21:22Z"), OrdemStatus.AGUARDANDO_PAGAMENTO, u1); /*Lembrando que o horario do Brasil é 3h atrasado em relação ao UTC. Por isso vai retornar com 3 horas a mais do que o obj*/
		
		usuarioRepositorio.saveAll(Arrays.asList(u1, u2)); /*chamando a classe usuarioRepositorio que acessa o bd, a patir dele chamamos o
		metodo saveAll que passa uma lista Arrays.asList e dentro dela os dois objetos que criamos acima*/
		
		ordemRepositorio.saveAll(Arrays.asList(o1, o2, o3));
	}
	
}
