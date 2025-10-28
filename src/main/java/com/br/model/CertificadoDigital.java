package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT NECESSÁRIO
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificado_digital")
public class CertificadoDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "tipo_publico")
    private String tipoPublico;

    @Column(name = "validade_meses")
    private Integer validadeMeses;

    private Double preco;

    @Column(name = "data_validade")
    private LocalDate dataValidade; // Usar LocalDate para o tipo DATE do Postgres

    private Boolean ativo;

    // Relacionamento N:1 com Orcamento (lado proprietário - Owner)
    // CORREÇÃO: Usar @JsonIgnore para evitar recursão infinita na serialização JSON.
    @JsonIgnore // <--- ADICIONADO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;

    // Relacionamento N:1 Opcional com Empresa (nulo se for PF)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa; // Opcional (Pode ser nulo)

    // ... Getters e Setters ...
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getTipoPublico() {
		return tipoPublico;
	}

	public void setTipoPublico(String tipoPublico) {
		this.tipoPublico = tipoPublico;
	}

	public Integer getValidadeMeses() {
		return validadeMeses;
	}

	public void setValidadeMeses(Integer validadeMeses) {
		this.validadeMeses = validadeMeses;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}