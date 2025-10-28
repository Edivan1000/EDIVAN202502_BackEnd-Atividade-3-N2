package com.br.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;
    
 // ... Getters e Setters ...

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

    // O relacionamento N:N com Cliente será gerenciado pela ClienteEmpresa.
    // É opcional mapear a coleção aqui, mas é bom para consulta.
    /*
    @ManyToMany(mappedBy = "empresas")
    private List<Cliente> clientes;
    */
    
}