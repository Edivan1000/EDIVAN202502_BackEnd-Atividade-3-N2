package com.br.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto_opcao")
public class ProdutoOpcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da opção (ex: "Validade de 12 meses", "Token A3", etc.)
    @Column(nullable = false)
    private String nome;

    // Descrição detalhada da opção
    @Column(length = 255)
    private String descricao;
    
    @Column(nullable = false)
    private Boolean ativo = true;

    // Preço adicional que essa opção acrescenta ao produto
    @Column(name = "preco_adicional", precision = 10, scale = 2)
    private BigDecimal precoAdicional;

    // Relação com Produto (muitas opções para um produto)
    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    @ManyToOne
    @JoinColumn(name="opcao_validade_id", nullable=false)
    private OpcaoValidade opcaoValidade;
    
    // Relação 1:N com CertificadoDigital (um opcional pode gerar vários certificados)
    @OneToMany(mappedBy = "produtoOpcao", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CertificadoDigital> certificados;

    // Construtores
    public ProdutoOpcao() {}

    public ProdutoOpcao(String nome, String descricao, BigDecimal precoAdicional, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoAdicional = precoAdicional;
        this.produto = produto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPrecoAdicional() { return precoAdicional; }
    public void setPrecoAdicional(BigDecimal precoAdicional) { this.precoAdicional = precoAdicional; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public List<CertificadoDigital> getCertificados() { return certificados; }
    public void setCertificados(List<CertificadoDigital> certificados) { this.certificados = certificados; }
    

    public Boolean getAtivo() {
    return ativo;
}

    public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
}
    
    public OpcaoValidade getOpcaoValidade() {
        return opcaoValidade;
    }

    public void setOpcaoValidade(OpcaoValidade opcaoValidade) {
        this.opcaoValidade = opcaoValidade;
    }
}
