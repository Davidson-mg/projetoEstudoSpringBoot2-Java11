package com.davidsonMarcos.projetoEstudo.recursos.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.davidsonMarcos.projetoEstudo.servicos.exceptions.DatabaseException;
import com.davidsonMarcos.projetoEstudo.servicos.exceptions.ResourceNotFoundException;

@ControllerAdvice /*Essa anotação serve para interceptar as exceções que ocorrem pra esse obj possa executar um possivel tratamento*/
public class ResourceExceptionHandler {

	@ExceptionHandler (ResourceNotFoundException.class) /*Serve para dizer que esse metodo vai interceptar qualquer exceção do tipo ResourceNotFoundException (personalizada) e tratar se possivel*/
	public ResponseEntity <StandardError> recursoNaoEncontrado (ResourceNotFoundException e, HttpServletRequest request){ /*Quando chamamos um obj que não existe, por padrão
	é retornado um "objeto" de erro com os mesmos atributos da classe StandardError. A classe StandardError nós criamos para personalizar esses atributos, e nesta classe em que 
	estamos, vamos instacia-la caso ocorra a exceção, só que agora com os atributos personalizados.*/
		
		String erro = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error = new StandardError (Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI()); /*status é do tipo Integer, por isso o .value() que 
		vai converter. Estamos instanciando a classe StandardError*/
		
		return ResponseEntity.status(status).body(error);
		
	}
	
	@ExceptionHandler (DatabaseException.class) /*Serve para dizer que esse metodo vai interceptar qualquer exceção do tipo DatabaseException (personalizada) e tratar se possivel*/
	public ResponseEntity <StandardError> dataBase (DatabaseException e, HttpServletRequest request){ /*Quando excluimos um obj que possui um servico (Ordem), por padrão
	é retornado um "objeto" de erro com os mesmos atributos da classe StandardError. A classe StandardError nós criamos para personalizar esses atributos, e nesta classe em que 
	estamos, vamos instacia-la caso ocorra a exceção, só que agora com os atributos personalizados.*/
		
		String erro = "Erro de Banco de Dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error = new StandardError (Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI()); /*status é do tipo Integer, por isso o .value() que 
		vai converter. Estamos instanciando a classe StandardError*/
		
		return ResponseEntity.status(status).body(error);
		
	}
	
	
}
