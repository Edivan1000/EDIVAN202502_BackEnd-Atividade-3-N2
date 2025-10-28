package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT (se o Cliente tiver lista de Orcamento)
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento N:1 com Cliente (Muitos orçamentos para um cliente)
    // CORREÇÃO: Usamos @JsonIgnore se Cliente tem uma lista de Orcamentos, para cortar o loop.
    // Se o cliente não tem a lista de Orcamentos, esta anotação pode ser removida.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;
    
    // ************************************************************
    // ** CÓDIGO DE CORS (WebConfig) FOI REMOVIDO DA ENTIDADE **
    // ************************************************************

    private String status;

    // Relacionamento 1:N com CertificadoDigital (O DETALHE)
    // Esta coleção é serializada (JsonManagedReference, implicitamente), não precisa de @JsonIgnore
    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CertificadoDigital> itens; // A lista dos itens do orçamento

    // ... Getters e Setters ...
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CertificadoDigital> getItens() {
		return itens;
	}

	public void setItens(List<CertificadoDigital> itens) {
		this.itens = itens;
	}
}