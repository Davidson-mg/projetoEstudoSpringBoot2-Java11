package com.davidsonMarcos.projetoEstudo.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidsonMarcos.projetoEstudo.entities.Ordem;
import com.davidsonMarcos.projetoEstudo.entities.Usuario;
import com.davidsonMarcos.projetoEstudo.servicos.OrdemServico;
import com.davidsonMarcos.projetoEstudo.servicos.UsuarioServico;

@RestController /*Necessario informar que a classe é um recurso wer implementado por um recurso rest*/
@RequestMapping(value = "/ordens") /*Necessario dar um nome para o meu recurso. Controlador Rest que responde ao caminho usuarios*/
public class OrdemRecurso {

	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private OrdemServico servico;
	
	@GetMapping /*Essa anotação serve para requisição HTTP*/
	public ResponseEntity<List<Ordem>> findAll(){ /*Um metodo para acessar os usuarios (Apenas teste). ResponseEntity é especifico do spring
	para retornar requisições web. O tipo da resposta vai ser uma lista de usuario (<List<Usuario>>)*/
		
		List <Ordem> list = servico.findAll();
		return ResponseEntity.ok().body(list); /*ResponseEntity retorna uma reposta de sucesso do http e no corpo da resposta (budy) a list*/
	}

	@GetMapping(value = "/{id}") /*Estou dizendo que minha requisição vai aceitar um "id" na minha url ao efetuar uma requisição HTTP. Ou seja, se eu
	colocar um numero de um id qualquer na url, vai retornar o obj referente aquele ID*/
	public ResponseEntity<Ordem>findById(@PathVariable Long id){ /*Não estou usando <List<Usuario>> pq vai retornar apenas um usuario por id.
	@PathVariable serve para o spring aceitar o parametro (neste caso id) como parametro na url*/
		
		Ordem obj = servico.findById(id); /*Buscando o obj pelo id que chegou como parametro*/
		return ResponseEntity.ok().body(obj);
	}
	
}
