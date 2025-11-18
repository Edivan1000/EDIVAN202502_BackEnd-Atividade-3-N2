package com.br.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria_certificado")
public class CategoriaCertificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name="nome")
    private String nome;

    // Construtor padrão
    public CategoriaCertificado() {
        super();
    }

    // Construtor com todos os campos
    public CategoriaCertificado(Long codigo, String nome) {
        super();
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
