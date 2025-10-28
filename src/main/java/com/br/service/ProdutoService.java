package com.br.service;

import com.br.model.ProdutoCatalogo;
import com.br.model.ProdutoOpcao;
import com.br.repository.ProdutoCatalogoRepository;
import com.br.repository.ProdutoOpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoCatalogoRepository catalogoRepository;

    @Autowired
    private ProdutoOpcaoRepository opcaoRepository;

    // Método para listar os produtos base (A1, A3) para o primeiro Select do Angular
    public List<ProdutoCatalogo> listarTodosProdutos() {
        return catalogoRepository.findAll();
    }
    
    // Método CRUCIAL para a lógica do Frontend: 
    // Dado um Produto, buscar suas opções de preço/validade.
    public List<ProdutoOpcao> buscarOpcoesPorProduto(Long produtoCatalogoId) {
        return opcaoRepository.findByCatalogoId(produtoCatalogoId);
    }
    
    // Método para buscar uma opção específica (para cálculo de preço final)
    public Optional<ProdutoOpcao> buscarOpcaoPorId(Long id) {
        return opcaoRepository.findById(id);
    }
    
    // Implementar aqui os métodos CRUD para manutenção dos catálogos (se necessário)
}