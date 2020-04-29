package com.davidsonMarcos.projetoEstudo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonMarcos.projetoEstudo.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> { /*Essa interface acessa o banco de dados. Vai ser responsavel por fazer
operações com a classe Usuario. Precisa extender JpaRepository passando o tipo de entidade (usuario) e o tipo da chave (long), que é referente
ao ID. Nesse caso não precisa criar a implementação da interface pois o fremeork já têm uma padrão imbutida. Criando dessa forma já é o suficinete*/

}
