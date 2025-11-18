package com.br.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class ItensOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Integer quantidade;

    // 🔗 Muitos itens pertencem a um orçamento
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orcamento_id")
    @JsonBackReference
    private Orcamento orcamento;

    // 🔗 Muitos itens podem referenciar um produto
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
