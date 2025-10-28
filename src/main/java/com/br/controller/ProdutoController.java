package com.br.controller;

import com.br.model.ProdutoCatalogo;
import com.br.model.ProdutoOpcao;
import com.br.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // 1. Diz ao Spring que esta classe manipula requisições REST
@RequestMapping("/api/produtos") // 2. Define o prefixo da URL para todos os métodos
@CrossOrigin(origins = "http://localhost:4200") // 3. Adicione agora para o futuro (CORS)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // GET /api/produtos/catalogo
    // Retorna todos os produtos base (A1, A3)
    @GetMapping("/catalogo")
    public List<ProdutoCatalogo> listarProdutosBase() {
        return produtoService.listarTodosProdutos();
    }

    // GET /api/produtos/opcoes?catalogoId=1
    // CRUCIAL: Retorna as opções de validade/preço de um produto específico
    @GetMapping("/opcoes")
    public List<ProdutoOpcao> buscarOpcoesPorProduto(@RequestParam Long catalogoId) {
        return produtoService.buscarOpcoesPorProduto(catalogoId);
    }
    
    // Opcional: Implementar CRUD para ADMIN gerenciar o catálogo se necessário
}