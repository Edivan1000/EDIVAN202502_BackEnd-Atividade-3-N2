package com.br.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 255)
    private String descricao;

    // novo campo para corrigir o erro no controller
    @Column(name = "preco_base", precision = 10, scale = 2)
    private BigDecimal precoBase;

    @Column(nullable = false)
    private boolean ativo = true;

    // Relacionamento 1:N com ProdutoOpcao
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProdutoOpcao> opcoes;

    // Construtores
    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal precoBase, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public List<ProdutoOpcao> getOpcoes() { return opcoes; }
    public void setOpcoes(List<ProdutoOpcao> opcoes) { this.opcoes = opcoes; }
}
