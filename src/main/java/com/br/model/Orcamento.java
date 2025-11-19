package com.br.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private LocalDate dataOrcamento;
	private Double totalOrcamento;

	// 🔗 Relacionamento 1:N com ItensOrcamento
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<ItensOrcamento> itens;

	// Getters e Setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataOrcamento() {
		return dataOrcamento;
	}

	public void setDataOrcamento(LocalDate dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}

	public Double getTotalOrcamento() {
		return totalOrcamento;
	}

	public void setTotalOrcamento(Double totalOrcamento) {
		this.totalOrcamento = totalOrcamento;
	}

	public List<ItensOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItensOrcamento> itens) {
		this.itens = itens;

		if (itens != null) {
			itens.forEach(i -> i.setOrcamento(this));
		}
	}
}
