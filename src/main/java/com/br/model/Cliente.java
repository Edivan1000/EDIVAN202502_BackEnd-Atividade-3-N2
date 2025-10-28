package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT NECESSÁRIO
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

	// ... Construtores (Opcional, mas útil) ...
	
    public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    // Relacionamento 1:1 com ClienteConfig
    // Mapeado pela entidade ClienteConfig (mappedBy)
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClienteConfig config;
    
    // Relacionamento 1:N com Orcamento
    // CORREÇÃO: Usar @JsonIgnore para evitar recursão infinita (Cliente -> Orcamento -> Cliente)
    @JsonIgnore // <--- ADICIONADO PARA CORTAR O LOOP
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orcamento> orcamentos;
    
    // ... Getters e Setters (Necessário para o Spring Boot/JPA) ...
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ClienteConfig getConfig() {
		return config;
	}

	public void setConfig(ClienteConfig config) {
		this.config = config;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}
}