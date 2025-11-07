package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.model.ProdutoOpcao;

@Repository
public interface ProdutoOpcaoRepository extends JpaRepository<ProdutoOpcao, Long> {
    // Aqui você pode adicionar consultas personalizadas, se quiser.
}
