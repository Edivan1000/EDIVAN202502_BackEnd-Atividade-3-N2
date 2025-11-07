package com.br.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Publico publico; // PF ou PJ

    @Column(name = "cpf_cnpj", length = 20)
    private String cpfCnpj;

    // Um cliente pode ter vários orçamentos (1:N)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Orcamento> orcamentos;

    // Relacionamento N:N com empresas
    @ManyToMany
    @JoinTable(
        name = "cliente_empresa",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "empresa_id")
    )
    private List<Empresa> empresas;

    // Enum para PF ou PJ
    public enum Publico {
        PF, // Pessoa Física
        PJ  // Pessoa Jurídica
    }

    // Construtores
    public Cliente() {}

    public Cliente(String nome, String email, String telefone, Publico publico) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.publico = publico;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Publico getPublico() { return publico; }
    public void setPublico(Publico publico) { this.publico = publico; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public List<Orcamento> getOrcamentos() { return orcamentos; }
    public void setOrcamentos(List<Orcamento> orcamentos) { this.orcamentos = orcamentos; }

    public List<Empresa> getEmpresas() { return empresas; }
    public void setEmpresas(List<Empresa> empresas) { this.empresas = empresas; }

}
