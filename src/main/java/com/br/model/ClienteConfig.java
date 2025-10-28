package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_config")
public class ClienteConfig {

    // PK/FK: Usa a PK do Cliente como sua própria PK
    @Id
    private Long id; 

    @Column(name = "preferencia_contato")
    private String preferenciaContato;

    private String observacoes;

    // Relacionamento 1:1 com Cliente (lado proprietário - Owner)
    @JsonIgnore // <--- CORTE O LOOP AQUI
    @OneToOne
    @MapsId // Garante que a PK desta entidade será a FK do Cliente (cliente_id)
    @JoinColumn(name = "id") // Nome da coluna FK no banco
    private Cliente cliente;

 // ... Getters e Setters ...
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreferenciaContato() {
		return preferenciaContato;
	}

	public void setPreferenciaContato(String preferenciaContato) {
		this.preferenciaContato = preferenciaContato;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    
}