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
	
}
