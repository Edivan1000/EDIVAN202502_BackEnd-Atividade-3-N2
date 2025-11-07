package com.br.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name="opcaovalidade")
public class OpcaoValidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Correto agora — em meses
    @Column(name="validademeses", nullable=false)
    private int validadeMeses; // 12, 24 ou 36 meses

    @Column(nullable=false)
    private BigDecimal preco;

    @Column(name="tipopublico", nullable=false)
    private String tipoPublico; // PF ou PJ

    @ManyToOne
    @JoinColumn(name="certificado_id")
    private CertificadoDigital certificado;

    @OneToMany(mappedBy="opcaoValidade", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<ProdutoOpcao> opcoes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getValidadeMeses() { return validadeMeses; }
    public void setValidadeMeses(int validadeMeses) { this.validadeMeses = validadeMeses; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getTipoPublico() { return tipoPublico; }
    public void setTipoPublico(String tipoPublico) { this.tipoPublico = tipoPublico; }

    public CertificadoDigital getCertificado() { return certificado; }
    public void setCertificado(CertificadoDigital certificado) { this.certificado = certificado; }

    public List<ProdutoOpcao> getOpcoes() { return opcoes; }
    public void setOpcoes(List<ProdutoOpcao> opcoes) { this.opcoes = opcoes; }
}
