package com.br.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="validade_meses")
    private int validadeMeses;

    @Column(name="preco")
    private double preco;

    @Column(name="disponivel")
    private boolean disponivel;

    @Column(name="quantidade")
    private int quantidade;

    @Column(name="tipo_pessoa")
    private String tipoPessoa; // PF ou PJ

    @Column(name="modalidade")
    private String modalidade; // Token Safenet, Cartão AWP, Arquivo A1…

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CategoriaCertificado categoriaCertificado;

    // Construtor padrão
    public Produto() {
        super();
    }

    // Construtor completo
    public Produto(Long codigo, String nome, String descricao, int validadeMeses, double preco,
                   boolean disponivel, int quantidade, String tipoPessoa, String modalidade,
                   CategoriaCertificado categoriaCertificado) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.validadeMeses = validadeMeses;
        this.preco = preco;
        this.disponivel = disponivel;
        this.quantidade = quantidade;
        this.tipoPessoa = tipoPessoa;
        this.modalidade = modalidade;
        this.categoriaCertificado = categoriaCertificado;
    }

    // Getters e Setters
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValidadeMeses() {
        return validadeMeses;
    }

    public void setValidadeMeses(int validadeMeses) {
        this.validadeMeses = validadeMeses;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public CategoriaCertificado getCategoriaCertificado() {
        return categoriaCertificado;
    }

    public void setCategoriaCertificado(CategoriaCertificado categoriaCertificado) {
        this.categoriaCertificado = categoriaCertificado;
    }
}
