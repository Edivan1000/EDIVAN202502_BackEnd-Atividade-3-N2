package com.br.repository;

import com.br.model.ProdutoCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCatalogoRepository extends JpaRepository<ProdutoCatalogo, Long> {
}