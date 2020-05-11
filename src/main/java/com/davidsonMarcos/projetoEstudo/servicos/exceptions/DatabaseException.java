package com.davidsonMarcos.projetoEstudo.servicos.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/*Essa exceção personalizada vai tratar o erro de integridade de BD ao tentar excluir um usuario que esteja associado a um servico (Ordem)*/
	
	public DatabaseException (String msg) { /*Um construtor onde passamos o Id do obj que tentamos encontrar*/
		
		super(msg); /*chamando o construtor da super classe (RuntimeException) e colocando nele uma msg de exceção*/
		
	}
	
}
