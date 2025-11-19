package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.EnderecoEmpresa;

@Repository
public interface EnderecoEmpresaRepository extends JpaRepository<EnderecoEmpresa, Long> {
}
