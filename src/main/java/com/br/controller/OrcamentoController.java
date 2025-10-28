package com.br.controller;

import com.br.model.Orcamento;
import com.br.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    // POST /api/orcamentos
    // Incluir / Alterar o Orçamento COMPLETO (Mestre + Detalhes)
    // O formulário principal do Angular enviará o ORCAMENTO inteiro aqui.
    @PostMapping
    public ResponseEntity<Orcamento> salvarOrcamentoCompleto(@RequestBody Orcamento orcamento) {
        try {
            Orcamento orcamentoSalvo = orcamentoService.salvarOrcamento(orcamento);
            return ResponseEntity.ok(orcamentoSalvo);
        } catch (Exception e) {
            // Em um ambiente real, você trataria erros específicos
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/orcamentos/1
    // Consultar (Busca um orçamento completo com todos os seus detalhes)
    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> consultarOrcamento(@PathVariable Long id) {
        return orcamentoService.buscarOrcamentoCompleto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/orcamentos
    // Listar (Todos os orçamentos)
    @GetMapping
    public List<Orcamento> listarOrcamentos() {
        return orcamentoService.listarOrcamentos(); 
        // Nota: Você precisaria implementar este método no OrcamentoService
        // para buscar a lista simples de orçamentos.
    }
    
    // Implementar métodos DELETE para Excluir o orçamento completo.
}