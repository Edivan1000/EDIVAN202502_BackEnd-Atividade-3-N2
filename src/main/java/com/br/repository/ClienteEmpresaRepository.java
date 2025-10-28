package com.br.repository;

import com.br.model.ClienteEmpresa;
import com.br.model.ClienteEmpresa.ClienteEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, ClienteEmpresaId> {
    // Métodos específicos para o N:N, ex:
    // List<ClienteEmpresa> findByClienteId(Long clienteId);
}