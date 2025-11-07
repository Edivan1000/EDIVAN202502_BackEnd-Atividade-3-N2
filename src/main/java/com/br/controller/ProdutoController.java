package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Produto;
import com.br.repository.ProdutoRepository;

@RestController
@RequestMapping("/cproduto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // ➤ Listar todos
    @GetMapping("/produto")
    public List<Produto> listar() {
        // Exemplo de endpoint completo: http://localhost:8080/cproduto/produto
        return produtoRepository.findAll();
    }

    // ➤ Consultar por ID
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> consultar(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
        return ResponseEntity.ok(produto);
    }

    // ➤ Incluir
    @PostMapping("/produto")
    public Produto incluir(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    // ➤ Alterar
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        existente.setNome(produto.getNome());
        existente.setDescricao(produto.getDescricao());
        existente.setPrecoBase(produto.getPrecoBase());
        existente.setAtivo(produto.isAtivo());
        existente.setOpcoes(produto.getOpcoes());

        Produto atualizado = produtoRepository.save(existente);
        return ResponseEntity.ok(atualizado);
    }

    // ➤ Excluir
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        produtoRepository.delete(existente);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Produto excluído!", true);
        return ResponseEntity.ok(resposta);
    }
}
