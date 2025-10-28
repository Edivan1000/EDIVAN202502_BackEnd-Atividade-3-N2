package com.br.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT NECESSÁRIO
import jakarta.persistence.*;

@Entity
@Table(name = "produto_opcao")
public class ProdutoOpcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "validade_meses")
    private Integer validadeMeses; // 12, 24, 36

    private Double preco;

    // Relacionamento N:1 com ProdutoCatalogo (lado proprietário - Owner)
    // CORREÇÃO: Usar @JsonIgnore para cortar o loop de serialização JSON.
    @JsonIgnore // <--- ADICIONADO PARA RESOLVER O SyntaxError
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_catalogo_id", nullable = false)
    private ProdutoCatalogo catalogo;

    // Construtor padrão (útil)
    public ProdutoOpcao() {
    }

    // --- Getters e Setters ---
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValidadeMeses() {
		return validadeMeses;
	}

	public void setValidadeMeses(Integer validadeMeses) {
		this.validadeMeses = validadeMeses;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

    // Mesmo com @JsonIgnore, o getter é mantido, mas não será serializado.
	public ProdutoCatalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(ProdutoCatalogo catalogo) {
		this.catalogo = catalogo;
	}
}