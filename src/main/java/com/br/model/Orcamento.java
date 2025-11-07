package com.br.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataSolicitacao;

    private BigDecimal valorTotal;

    // Cada orçamento está ligado a um cliente (1:1)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"orcamentos"}) // ignora lista de orçamentos dentro de cliente
    private Cliente cliente;

    // Um orçamento pode ter vários certificados digitais (1:N)
    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"orcamento", "produtoOpcao", "produto"})
    private List<CertificadoDigital> certificados;

    // Construtores
    public Orcamento() {}

    public Orcamento(LocalDate dataSolicitacao, BigDecimal valorTotal, Cliente cliente, List<CertificadoDigital> certificados) {
        this.dataSolicitacao = dataSolicitacao;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.certificados = certificados;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<CertificadoDigital> getCertificados() { return certificados; }
    public void setCertificados(List<CertificadoDigital> certificados) { this.certificados = certificados; }

}
