package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.EnderecoCliente;

@Repository
public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
}
