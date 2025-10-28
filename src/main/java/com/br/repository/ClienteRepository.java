package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}