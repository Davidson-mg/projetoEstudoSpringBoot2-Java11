package com.davidsonMarcos.projetoEstudo.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidsonMarcos.projetoEstudo.entities.Ordem;
import com.davidsonMarcos.projetoEstudo.repositorios.OrdemRepositorio;


@Service /*Essa anotação serve para que a classe possa ser usada nas injeções de dependecia do spring usando o @Autowired. Existem outras 
anotações como "@Component" (registrar um componente) e "@Repository" (registrar um repositorio). Neste caso estamos usando o @Service pq
esta classe é um servico */
public class OrdemServico {

	@Autowired /*Vai resolver a dependencia com a classe OrdemRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private OrdemRepositorio repositorio;
	
	public List<Ordem> findAll (){ /*Este metodo vai buscar todos os objetos do BD*/
		
		return repositorio.findAll();
		
	}
	
	public Ordem findById (Long id) {
		
		Optional<Ordem> obj = repositorio.findById(id);
		return obj.get();
		
	}
	
}
