package com.br.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String razaoSocial;

	private String nomeFantasia;

	private String cnpj;

	private String email;

	private String telefone;

	// Relacionamento 1:1 com EnderecoEmpresa
	@OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL)
	@JsonManagedReference
	private EnderecoEmpresa endereco;

	@OneToMany(mappedBy = "empresa")
	private List<ClienteEmpresa> clientes = new ArrayList<>();
	// Cada Empresa pode ter vários Clientes via ClienteEmpresa (relacionamento N:N)

	// GETTERS E SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public EnderecoEmpresa getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEmpresa endereco) {
		this.endereco = endereco;
		if (endereco != null) {
			endereco.setEmpresa(this);
		}
	}
}
