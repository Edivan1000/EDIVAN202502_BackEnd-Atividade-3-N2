package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Produto;
import com.br.repository.ProdutoRepository;

@RestController
@RequestMapping("/cproduto/")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository prodRep;

    // LISTAR
    @GetMapping("/produto")
    public List<Produto> listar() {
        return prodRep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }

    // CONSULTAR
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> consultar(@PathVariable Long id) {

        Produto produto = prodRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        return ResponseEntity.ok(produto);
    }

    // INSERIR
    @PostMapping("/produto")
    public Produto incluir(@RequestBody Produto produto) {
        return prodRep.save(produto);
    }

    // EXCLUIR
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {

        Produto produto = prodRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        prodRep.delete(produto);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Produto Excluído!", true);

        return ResponseEntity.ok(resposta);
    }

    // ALTERAR
    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produto) {

        Produto prodLoc = prodRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));

        // NÃO atualizar o código!
        prodLoc.setNome(produto.getNome());
        prodLoc.setDescricao(produto.getDescricao());
        prodLoc.setValidadeMeses(produto.getValidadeMeses());
        prodLoc.setPreco(produto.getPreco());
        prodLoc.setDisponivel(produto.isDisponivel());
        prodLoc.setQuantidade(produto.getQuantidade());
        prodLoc.setTipoPessoa(produto.getTipoPessoa());
        prodLoc.setModalidade(produto.getModalidade());
        prodLoc.setCategoriaCertificado(produto.getCategoriaCertificado());

        Produto atualizado = prodRep.save(prodLoc);

        return ResponseEntity.ok(atualizado);
    }
}
