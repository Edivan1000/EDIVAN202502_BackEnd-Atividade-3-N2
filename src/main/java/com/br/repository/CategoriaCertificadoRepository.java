package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.model.CategoriaCertificado;

@Repository
public interface CategoriaCertificadoRepository extends JpaRepository<CategoriaCertificado, Long> {

}
