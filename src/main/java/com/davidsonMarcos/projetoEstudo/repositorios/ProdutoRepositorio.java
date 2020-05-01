package com.davidsonMarcos.projetoEstudo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonMarcos.projetoEstudo.entities.Produto;
import com.davidsonMarcos.projetoEstudo.entities.Ordem;

/*Não precisa colocar a anotão de dependencia @Repository pq essa interface está herdando da classe JpaRepository que já está registrado como componente do spring*/
public interface ProdutoRepositorio extends JpaRepository <Produto, Long> { /*Essa interface acessa o banco de dados. Vai ser responsavel por fazer
operações com a classe Ordem. Precisa extender JpaRepository passando o tipo de entidade (usuario) e o tipo da chave (long), que é referente
ao ID. Nesse caso não precisa criar a implementação da interface pois o fremeork já têm uma padrão imbutida. Criando dessa forma já é o suficinete*/

}
