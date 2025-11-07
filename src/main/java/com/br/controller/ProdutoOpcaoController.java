package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.ProdutoOpcao;
import com.br.repository.OpcaoValidadeRepository;
import com.br.repository.ProdutoOpcaoRepository;
import com.br.repository.ProdutoRepository;

@RestController
@RequestMapping("/cprodutoopcao")
@CrossOrigin(origins = "*")
public class ProdutoOpcaoController {

    @Autowired
    private ProdutoOpcaoRepository produtoOpcaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OpcaoValidadeRepository opcaoValidadeRepository;

    // ➤ Listar todos
    @GetMapping("/produtoopcao")
    public List<ProdutoOpcao> listar() {
        return produtoOpcaoRepository.findAll();
    }

    // ➤ Consultar por ID
    @GetMapping("/produtoopcao/{id}")
    public ResponseEntity<ProdutoOpcao> consultar(@PathVariable Long id) {
        ProdutoOpcao opcao = produtoOpcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto opção não encontrado."));
        return ResponseEntity.ok(opcao);
    }

    // ➤ Incluir
    @PostMapping("/produtoopcao")
    public ProdutoOpcao incluir(@RequestBody ProdutoOpcao produtoOpcao) {

        // Validar e carregar Produto
        if (produtoOpcao.getProduto() == null || produtoOpcao.getProduto().getId() == null) {
            throw new ResourceNotFoundException("Produto não informado.");
        }
        produtoOpcao.setProduto(
            produtoRepository.findById(produtoOpcao.getProduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."))
        );

        // Validar e carregar OpcaoValidade
        if (produtoOpcao.getOpcaoValidade() == null || produtoOpcao.getOpcaoValidade().getId() == null) {
            throw new ResourceNotFoundException("Opção de validade não informada.");
        } else {
            produtoOpcao.setOpcaoValidade(
                opcaoValidadeRepository.findById(produtoOpcao.getOpcaoValidade().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Opção de validade não encontrada."))
            );
        }

        return produtoOpcaoRepository.save(produtoOpcao);
    }

    // ➤ Alterar
    @PutMapping("/produtoopcao/{id}")
    public ResponseEntity<ProdutoOpcao> alterar(@PathVariable Long id, @RequestBody ProdutoOpcao produtoOpcao) {
        ProdutoOpcao existente = produtoOpcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto opção não encontrado."));

        // Validar e atualizar OpcaoValidade
        if (produtoOpcao.getOpcaoValidade() == null || produtoOpcao.getOpcaoValidade().getId() == null) {
            throw new ResourceNotFoundException("Opção de validade não informada.");
        }
        existente.setOpcaoValidade(
            opcaoValidadeRepository.findById(produtoOpcao.getOpcaoValidade().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Opção de validade não encontrada."))
        );

        // Validar e atualizar Produto
        if (produtoOpcao.getProduto() == null || produtoOpcao.getProduto().getId() == null) {
            throw new ResourceNotFoundException("Produto não informado.");
        }
        existente.setProduto(
            produtoRepository.findById(produtoOpcao.getProduto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."))
        );

        existente.setNome(produtoOpcao.getNome());
        existente.setDescricao(produtoOpcao.getDescricao());
        existente.setPrecoAdicional(produtoOpcao.getPrecoAdicional());
        existente.setCertificados(produtoOpcao.getCertificados());
        existente.setAtivo(produtoOpcao.getAtivo());

        ProdutoOpcao atualizado = produtoOpcaoRepository.save(existente);
        return ResponseEntity.ok(atualizado);
    }

    // ➤ Excluir
    @DeleteMapping("/produtoopcao/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        ProdutoOpcao existente = produtoOpcaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto opção não encontrado."));

        produtoOpcaoRepository.delete(existente);
        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Produto opção excluído!", true);
        return ResponseEntity.ok(resposta);
    }
}
