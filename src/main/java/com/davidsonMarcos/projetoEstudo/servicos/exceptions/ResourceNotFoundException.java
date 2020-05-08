package com.davidsonMarcos.projetoEstudo.servicos.exceptions;

public class ResourceNotFoundException extends RuntimeException {/*Essa é uma classe de execeções personalizadas e é também uma subclasse da classe do java 
	RuntimeException. Lembrando que RuntimeException não te obriga a tratar*/
	
	private static final long serialVersionUID = 1L; 
	
	/*Se eu tentar buscar um obj por um id que não existe, sem essa classe de exceção personalizada, retornaria um erro 500. O mais indicado para um obj que não
	existe é o erro 404. Uma das funções dessa classe será resolver isso. Criamos também a classe StandarError no pacote "recursos.execeptions" que personaliza 
	o objeto de resposta padrão para este erro que é retornado e a classe ResourceExceptionHandler que vai tratar as exceções */
	
	public ResourceNotFoundException (Object id) { /*Um construtor onde passamos o Id do obj que tentamos encontrar*/
		
		super ("Recurso não encontrado. Id "+id); /*chamando o construtor da super classe (RuntimeException) e colocando nele uma msg de exceção*/
		
	}

}
