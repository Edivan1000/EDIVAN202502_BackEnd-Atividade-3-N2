package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Aqui você pode adicionar consultas personalizadas se precisar, por exemplo:
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
}
