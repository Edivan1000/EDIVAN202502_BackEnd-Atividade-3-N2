package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.ClienteEmpresa;

@Repository
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, Long> {
}
