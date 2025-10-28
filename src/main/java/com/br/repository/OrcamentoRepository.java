package com.br.repository;

import com.br.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    
    // Método útil: buscar orçamentos de um cliente
    List<Orcamento> findByClienteId(Long clienteId);
}