package com.br.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "produto_catalogo")
public class ProdutoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome; // Ex: Certificado A1

    private String descricao;

    // Relacionamento 1:N com ProdutoOpcao (as opções de validade/preço)
    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoOpcao> opcoes;
    
    // ... Getters e Setters ...

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProdutoOpcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<ProdutoOpcao> opcoes) {
		this.opcoes = opcoes;
	}


}