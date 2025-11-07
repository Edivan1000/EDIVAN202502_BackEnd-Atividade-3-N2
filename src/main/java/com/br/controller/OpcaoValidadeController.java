package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.CertificadoDigital;
import com.br.model.OpcaoValidade;
import com.br.repository.CertificadoDigitalRepository;
import com.br.repository.OpcaoValidadeRepository;

@RestController
@RequestMapping("/copcaovalidade")
@CrossOrigin(origins = "*")
public class OpcaoValidadeController {

    @Autowired
    private OpcaoValidadeRepository opRepo;

    @Autowired
    private CertificadoDigitalRepository certRepo;

    // LISTAR TODAS AS OPÇÕES DE VALIDADE
    @GetMapping("/opcoes")
    public List<OpcaoValidade> listar() {
        return opRepo.findAll();
    }

    // CONSULTAR OPÇÃO ESPECÍFICA
    @GetMapping("/opcoes/{id}")
    public ResponseEntity<OpcaoValidade> consultar(@PathVariable Long id) {
        OpcaoValidade op = opRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opção de validade não encontrada"));
        return ResponseEntity.ok(op);
    }

    // INCLUIR UMA NOVA OPÇÃO DE VALIDADE
    @PostMapping("/opcoes")
    public ResponseEntity<OpcaoValidade> incluir(@RequestBody OpcaoValidade op) {
        // Certificado precisa existir
        CertificadoDigital cert = certRepo.findById(op.getCertificado().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Certificado não encontrado"));
        op.setCertificado(cert);

        OpcaoValidade salvo = opRepo.save(op);
        return ResponseEntity.ok(salvo);
    }

    // ALTERAR UMA OPÇÃO EXISTENTE
    @PutMapping("/opcoes/{id}")
    public ResponseEntity<OpcaoValidade> alterar(@PathVariable Long id, @RequestBody OpcaoValidade op) {
        OpcaoValidade existente = opRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opção de validade não encontrada"));

        existente.setValidadeMeses(op.getValidadeMeses());
        existente.setPreco(op.getPreco());
        existente.setTipoPublico(op.getTipoPublico());

        if (op.getCertificado() != null) {
            CertificadoDigital cert = certRepo.findById(op.getCertificado().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Certificado não encontrado"));
            existente.setCertificado(cert);
        }

        OpcaoValidade atualizado = opRepo.save(existente);
        return ResponseEntity.ok(atualizado);
    }

    // EXCLUIR OPÇÃO
    @DeleteMapping("/opcoes/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        OpcaoValidade op = opRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opção de validade não encontrada"));

        opRepo.delete(op);

        Map<String, Boolean> resp = new HashMap<>();
        resp.put("excluido", true);
        return ResponseEntity.ok(resp);
    }
}
