package com.davidsonMarcos.projetoEstudo.recursos;

import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.davidsonMarcos.projetoEstudo.entities.Usuario;
import com.davidsonMarcos.projetoEstudo.servicos.UsuarioServico;

@RestController /*Necessario informar que a classe é um recurso wer implementado por um recurso rest*/
@RequestMapping(value = "/usuarios") /*Necessario dar um nome para o meu recurso. Controlador Rest que responde ao caminho usuarios*/
public class UsuarioRecurso {
	
	@Autowired /*Vai resolver a dependencia com a classe UsuarioRepositorio (acessa o BD) sem precisar de construtor, metodo set, fabrica e etc*/
	private UsuarioServico servico;
	
	@GetMapping /*Essa anotação serve para requisição HTTP*/
	public ResponseEntity<List<Usuario>> findAll(){ /*Um metodo para acessar os usuarios (Apenas teste). ResponseEntity é especifico do spring
	para retornar requisições web. O tipo da resposta vai ser uma lista de usuario (<List<Usuario>>)*/
		
		List <Usuario> list = servico.findAll();
		return ResponseEntity.ok().body(list); /*ResponseEntity retorna uma reposta de sucesso do http e no corpo da resposta (budy) a list*/
	}

	@GetMapping(value = "/{id}") /*Estou dizendo que minha requisição vai aceitar um "id" na minha url ao efetuar uma requisição HTTP (no postman). Ou seja, se eu
	colocar um numero de um id qualquer na url, vai retornar o obj referente aquele ID*/
	public ResponseEntity<Usuario>findById(@PathVariable Long id){ /*Não estou usando <List<Usuario>> pq vai retornar apenas um usuario por id.
	@PathVariable serve para o spring aceitar o parametro (neste caso id) como parametro na url*/
		
		Usuario obj = servico.findById(id); /*Buscando o obj pelo id que chegou como parametro*/
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity <Usuario> insert (@RequestBody Usuario obj){ /*Essa anotação @RequestBody serve pra dizer que o obj vai chegar no modo jSon ao fazer requisição
	e ser desserializado para o obj Usuario. Metodo para inserir um novo usuario */
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); /*Quando inserimos um obj, nós recebemos uma
		200 e devemos retornar um 201. Essa linha de comando vai retornar o cabeçalho do tipo location contendo o endereço do novo obj inserido, e que será solicitado
		abaixo em ".created(uri)"*/
		obj = servico.insert(obj);
		return ResponseEntity.created(uri).body(obj); /*O created espera uma variavel do tipo uri. Isso acontece pq padrão http quando vc retorna um 201, a reposta
		deve conter um cabeçalho chamado location contendo o endereço do novo obj inserido. Quando testarmos inserindo um novo obj, deve retornar no postman o
		status "201 created", e na aba "Headers" (referente o resultado) deve retornar em "Location" o caminho do novo obj.*/
		
	}
	
	/*Pra realizar um teste de inserção usando o postman, do lado da URL deve selecionar a opção "POST", em seguida ir na aba "Body" e marcar aopção "row".
	  Na URL vc dever colocar o final "/usuario" após o 8080". É necessario também selecionar a opção JSON na barra de opções e verificar na aba "Headers" se a
	  opção "content-type" está marcada. Por fim, basta inserir na aba body conforme o exemplo abaixo 
	  
	  {     
	  "nome": "Bob Brown",     
	  "email": "bob@gmail.com",     
	  "phone": "977557755",     
	  "password": "123456" 
	  }
	  
	  */
	
	
	@DeleteMapping (value = "/{id}") /*se vai deletar por id então precisa passar o id*/
	public ResponseEntity<Void> delete (@PathVariable Long id){ /*@PathVariable serve para o spring aceitar o parametro (neste caso id) como parametro na url*/
		
		servico.delete(id);
		return ResponseEntity.noContent().build(); /*Em caso de delete a resposta não têm corpo, por isso usamos o metodo noContent que retorna uma resposta vazia.
		O codigo http de uma resposta vazia é 204 e o metodo noContent já vai tratar isso.*/
		
		/*OBS: se neste ponto se eu tentar excluir um usuario associado a um pedido, vai retornar exceção de integridade do banco de dados. Vamos tratar isso nos
		proximos commits. Neste momento podemos excluir somente usuarios que não têm pedidos*/
		
	}
	
	/*Para realizar um teste de exlusão usando o postman, do lado da URL deve selecionar a opção "DELETE" e na url inserir o caminho com o id no final e clicar em Sand.
	 * ex: http://localhost:8080/usuarios/3 */
	
}

