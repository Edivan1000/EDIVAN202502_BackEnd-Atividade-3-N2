package com.br.repository;

import com.br.model.CertificadoDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoDigitalRepository extends JpaRepository<CertificadoDigital, Long> {
    
    // Método para listar todos os itens de um orçamento específico
    List<CertificadoDigital> findByOrcamentoId(Long orcamentoId);
}