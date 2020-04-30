package com.davidsonMarcos.projetoEstudo.entities.enums;

public enum OrdemStatus {
	
	/*Estes numeros do lado são apenas para especificar o valor de cada stutus, mas lembrando que não é orbigatoro. Por conta destes numeros, tive que criar 
	 * abaixo um atributo, o construtor e um metodo get para acessar os valores*/

	AGUARDANDO_PAGAMENTO(1), 
	PAGO(2),
	ENVIADO(3),
	ENTREGUE(4),
	CANCELADO(5);
	
	private int codigo;
	
	private OrdemStatus (int codigo) { 
		
		this.codigo = codigo;
		
	}
	
	public int getCodigo () {
		
		return codigo;
		
	}
	
	public static OrdemStatus valueOf (int codigo) { /*Esse metodo vai receber um numero e ver se possui algum status (numero entre parentese) igual.
	Se tiver, ele vai retornar o status referente ao numero*/
		
		for(OrdemStatus valor : OrdemStatus.values()) {
			
			if(valor.getCodigo() == codigo) {
				
				return valor;
				
			}
			
		}
		
		throw new IllegalArgumentException("Codigo da ordem de status é incorreto!");
		
	}
}
