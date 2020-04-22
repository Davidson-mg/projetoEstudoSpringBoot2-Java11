package com.davidsonMarcos.projetoEstudo.recursos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidsonMarcos.projetoEstudo.entities.Usuario;

@RestController /*Necessario informar que a classe é um recurso wer implementado por um recurso rest*/
@RequestMapping(value = "/usuarios") /*Necessario dar um nome para o meu recurso. Controlador Rest que responde ao caminho usuarios*/
public class UsuarioRecurso {

	@GetMapping
	public ResponseEntity<Usuario> findAll(){ /*Um metodo para acessar os usuarios (Apenas teste). ResponseEntity é especifico do spring
	para retornar requisições web*/
		
		Usuario u = new Usuario(1L, "Davidson", "davidson@", "7777-7777", "12345"); /*instanciando o meu usuario com meu construtor*/
		return ResponseEntity.ok().body(u);
	}
	
}
