package com.br.repository;

import com.br.model.ProdutoOpcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoOpcaoRepository extends JpaRepository<ProdutoOpcao, Long> {
    
    // Método crucial para a lógica de Frontend: 
    // Buscar as opções (validade/preço) de um produto específico
    List<ProdutoOpcao> findByCatalogoId(Long catalogoId);
}