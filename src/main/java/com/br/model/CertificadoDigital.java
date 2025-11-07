package com.br.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.*;

@Entity
@Table(name="certificadodigital")
public class CertificadoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="ativo")
    private boolean ativo;

    // RELACIONAMENTO COM ORCAMENTO (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @JsonBackReference
    private Orcamento orcamento;
    
    @ManyToOne
    @JoinColumn(name = "produto_opcao_id")
    @JsonIgnoreProperties({"certificados"})
    private ProdutoOpcao produtoOpcao;

    public ProdutoOpcao getProdutoOpcao() { return produtoOpcao; }
    public void setProdutoOpcao(ProdutoOpcao produtoOpcao) { this.produtoOpcao = produtoOpcao; }
    
    @OneToMany(mappedBy="certificado", cascade=CascadeType.ALL)
    private List<OpcaoValidade> opcoesValidade; // PF/PJ + 1,2,3 anos


    // Construtor padrão
    public CertificadoDigital() {
        super();
    }

    // Construtor com todos os campos, sem o Orcamento (pode setar depois)
    public CertificadoDigital(Long id, String nome, boolean ativo) {
        super();
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome (String nome) { this.nome = nome; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Orcamento getOrcamento() { return orcamento; }
    public void setOrcamento(Orcamento orcamento) { this.orcamento = orcamento; }

}
