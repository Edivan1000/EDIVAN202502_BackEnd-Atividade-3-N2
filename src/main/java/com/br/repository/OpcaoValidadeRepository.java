package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.model.OpcaoValidade;

@Repository
public interface OpcaoValidadeRepository extends JpaRepository<OpcaoValidade, Long> {
    // Aqui podemos adicionar métodos customizados se precisar no futuro
}
