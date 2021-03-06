package com.davidsonMarcos.projetoEstudo.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.davidsonMarcos.projetoEstudo.repositorios.UsuarioRepositorio;
import com.davidsonMarcos.projetoEstudo.servicos.exceptions.DatabaseException;
import com.davidsonMarcos.projetoEstudo.servicos.exceptions.ResourceNotFoundException;
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
		return obj.orElseThrow(() -> new ResourceNotFoundException (id)); /*Nós trocamos o ".get" por "orElseThrow". Quer dizer que ele vai tentar dar um get, se o usuario não existir,, 
		ele vai lançar a exceção personalizada do pacote .servicos.exceptions*/
		
	}
	
	public Usuario insert (Usuario obj) { /*Vai inserir um novo usuario*/
		
		return repositorio.save(obj);
		
	}
	
	public void delete (Long id) {
		try {
			
			repositorio.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) { /*Caso o usuario tente deletar um id que não existe, para que não retorne um erro 500, e seja tratado pela nossa exceção 
		personalizada ResourceNotFoundException. Antes de usar o EmptyResultDataAccessException, eu fiz um teste com a exceção RumtimeException (que é generica) e entre chaves 
		e.printStackTrace(). Em seguida rodei o programa e selecionei um id inexistente. Eu fiz isso para saber qual era a exceção pra esse tipo de caso, que é a 
		EmptyResultDataAccessException. A mesma coisa foi feita no catch abaixo*/
			
			throw new ResourceNotFoundException(id); /*Lanaçando minha exceção personalizada que trata o erro de excluir um usuario não existente*/
			
		}catch (DataIntegrityViolationException e) {
			
			throw new DatabaseException(e.getMessage()); /*Lanaçando minha exceção personalizada que trata o erro de excluir um usuario associado a um serço (Ordem)*/
			
		}	
	}
	
	public Usuario update (Long id, Usuario obj) { /*Vai retornar o usuario atualizado. Vai receber o id pra indicar qual usuario vai atualizar e um Usuario com os dados dele*/
		
		try {
			
			Usuario entidade = repositorio.getOne(id); /*O getOne não vai no BD ainda, ele pega o obj monitorado pelo jpa pra gente poder trabalhar com ele e em seguida 
			efetuar alguma alteração no BD. É melhor do que o findById pq vai direto no Banco de Dados. */
			atualizarDados (entidade, obj); /*Esse metodo atualiza os dados da minha entidade baseado nos dados que chegaram no meu obj*/
			return repositorio.save(entidade); /*Depois disso nós salvamos no nosso banco de dados*/
			
		}catch(EntityNotFoundException e) { /*Caso o usuario tente deletar um id que não existe, para que não retorne um erro 500, e seja tratado pela nossa exceção 
			personalizada ResourceNotFoundException. Antes de usar o EmptyResultDataAccessException, eu fiz um teste com a exceção RumtimeException (que é generica) e entre chaves 
			e.printStackTrace() e dessa vez com o throw new ResourceNotFoundException(id); é necessario retornar algo ou gerar a exceção. Em seguida rodei o programa e selecionei 
			um id inexistente. Eu fiz isso para saber qual era a exceção pra esse tipo de caso, que é a EmptyResultDataAccessException.*/
				
			throw new ResourceNotFoundException(id);
			
		}
			
		
	}

	private void atualizarDados(Usuario entidade, Usuario obj) {
		
		entidade.setNome(obj.getNome()); /*Atualizando os dados da entidade com base no que chegou no obj*/
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
		
		/*Repare que não atualizamos o id e nem a senha.*/
		
	}
	
}
