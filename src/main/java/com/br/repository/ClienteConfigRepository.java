package com.br.repository;

import com.br.model.ClienteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteConfigRepository extends JpaRepository<ClienteConfig, Long> {
    // Para relacionamentos 1:1 onde a PK é a FK, o tipo da chave é Long.
}