package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cliente_empresa")
public class ClienteEmpresa implements Serializable {

    // ... (restante do código da chave composta e ID) ...

    @EmbeddedId
    private ClienteEmpresaId id = new ClienteEmpresaId();

    // Relacionamento ManyToOne para Cliente
    // CORREÇÃO: Adicionado @JsonIgnore para cortar o loop ClienteEmpresa -> Cliente -> ClienteEmpresa
    @JsonIgnore 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clienteId") 
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento ManyToOne para Empresa
    // CORREÇÃO: Adicionado @JsonIgnore para cortar o loop ClienteEmpresa -> Empresa -> ClienteEmpresa
    @JsonIgnore 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empresaId") 
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Classe auxiliar para a Chave Composta (Embeddable)
    @Embeddable
    public static class ClienteEmpresaId implements Serializable {
        @Column(name = "cliente_id")
        private Long clienteId;
        
        @Column(name = "empresa_id")
        private Long empresaId;
        
        // FALTAM hashCode e equals AQUI!
        // Se você não os tem, isso é um erro grave de JPA, mas não é a causa do loop JSON.
    }
    
    // ... Getters e Setters, Construtores ...

	public ClienteEmpresaId getId() {
		return id;
	}

	public void setId(ClienteEmpresaId id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
    
    
    

}