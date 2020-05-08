package com.davidsonMarcos.projetoEstudo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidsonMarcos.projetoEstudo.repositorios.UsuarioRepositorio;

import com.davidsonMarcos.projetoEstudo.entities.Usuario;

@Service /*Essa anotação serve para que a classe possa ser usada nas injeções de dependecia do spring usando o @Autowired. Existem outras 
anotações como "@Component" (registrar um componente) e "@Repository" (registrar um repositorio). Neste caso estamos usando o @Service pq
esta classe é um servico */
public class UsuarioServico {

	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> findAll (){ /*Este metodo vai buscar todos os objetos do BD*/
		
		return repositorio.findAll();
		
	}
	
	public Usuario findById (Long id) {
		
		Optional<Usuario> obj = repositorio.findById(id);
		return obj.get();
		
	}
	
	public Usuario insert (Usuario obj) { /*Vai inserir um novo usuario*/
		
		return repositorio.save(obj);
		
	}
	
	public void delete (Long id) {
		
		repositorio.deleteById(id);
		
	}
	
	public Usuario update (Long id, Usuario obj) { /*Vai retornar o usuario atualizado. Vai receber o id pra indicar qual usuario vai atualizar e um Usuario com os dados dele*/
		
		Usuario entidade = repositorio.getOne(id); /*O getOne não vai no BD ainda, ele pega o obj monitorado pelo jpa pra gente poder trabalhar com ele e em seguida 
		efetuar alguma alteração no BD. É melhor do que o findById pq vai direto no Banco de Dados. */
		atualizarDados (entidade, obj); /*Esse metodo atualiza os dados da minha entidade baseado nos dados que chegaram no meu obj*/
		return repositorio.save(entidade); /*Depois disso nós salvamos no nosso banco de dados*/
		
	}

	private void atualizarDados(Usuario entidade, Usuario obj) {
		
		entidade.setNome(obj.getNome()); /*Atualizando os dados da entidade com base no que chegou no obj*/
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
		
		/*Repare que não atualizamos o id e nem a senha.*/
		
	}
	
}
