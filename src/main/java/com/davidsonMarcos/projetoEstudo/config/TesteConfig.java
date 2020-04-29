package com.davidsonMarcos.projetoEstudo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.davidsonMarcos.projetoEstudo.entities.Usuario;
import com.davidsonMarcos.projetoEstudo.repositorios.UsuarioRepositorio;

@Configuration /*Classe de configuração é uma classe auxiliar que vai fazer algumas configurações na aplicação. É preciso informar com o @Configuration*/
@Profile("test") /*Vai rodar essa configuração somente quando estivermos no perfil de teste*/
public class TesteConfig implements CommandLineRunner { 

	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void run(String... args) throws Exception { /*Tudo que estiver neste metodo vai ser executado quando a aplicação for executada.
	É um metodo da interface CommandLineRunner. Instanciamos e salvamos no BD nossos objetos dentro deste metodo.*/
		
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		usuarioRepositorio.saveAll(Arrays.asList(u1, u2)); /*chamando a classe usuarioRepositorio que acessa o bd, a patir dele chamamos o
		metodo saveAll que passa uma lista Arrays.asList e dentro dela os dois objetos que criamos acima*/
		
	}
	
}
