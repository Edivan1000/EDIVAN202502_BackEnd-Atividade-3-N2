package com.br.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Orcamento;
import com.br.model.Cliente;
import com.br.model.CertificadoDigital;
import com.br.repository.OrcamentoRepository;
import com.br.repository.ClienteRepository;
import com.br.repository.CertificadoDigitalRepository;

@RestController
@RequestMapping("/corcamento")
@CrossOrigin(origins = "*")
public class OrcamentoController {

    @Autowired
    private OrcamentoRepository autorep;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CertificadoDigitalRepository certificadoDigitalRepository;

    // Listar todos
    @GetMapping("/orcamento")
    public List<Orcamento> listar() {
        return autorep.findAll();
    }

    // Consultar por ID
    @GetMapping("/orcamento/{id}")
    public ResponseEntity<Orcamento> consultar(@PathVariable Long id) {
        Orcamento auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado."));
        return ResponseEntity.ok(auto);
    }

    // Incluir novo
    @PostMapping("/orcamento")
    public ResponseEntity<Orcamento> incluir(@RequestBody Orcamento orcamento) {
        // Buscar cliente existente
        Cliente cliente = clienteRepository.findById(orcamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Buscar certificados existentes
        List<CertificadoDigital> certificados = new ArrayList<>();
        for (CertificadoDigital cert : orcamento.getCertificados()) {
            CertificadoDigital existente = certificadoDigitalRepository.findById(cert.getId())
                    .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
            certificados.add(existente);
        }

        // Reatribuir
        orcamento.setCliente(cliente);
        orcamento.setCertificados(certificados);

        Orcamento salvo = autorep.save(orcamento);
        return ResponseEntity.ok(salvo);
    }

    // Alterar existente
    @PutMapping("/orcamento/{id}")
    public ResponseEntity<Orcamento> alterar(@PathVariable Long id, @RequestBody Orcamento orcamento) {
        Orcamento auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado."));

        auto.setDataSolicitacao(orcamento.getDataSolicitacao());
        auto.setValorTotal(orcamento.getValorTotal());

        // Atualiza cliente e certificados
        Cliente cliente = clienteRepository.findById(orcamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        auto.setCliente(cliente);

        List<CertificadoDigital> certificados = new ArrayList<>();
        for (CertificadoDigital cert : orcamento.getCertificados()) {
            CertificadoDigital existente = certificadoDigitalRepository.findById(cert.getId())
                    .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
            certificados.add(existente);
        }
        auto.setCertificados(certificados);

        Orcamento atualizado = autorep.save(auto);
        return ResponseEntity.ok(atualizado);
    }

    // Excluir
    @DeleteMapping("/orcamento/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Orcamento auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orçamento não encontrado."));

        autorep.delete(auto);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Orçamento excluído!", true);
        return ResponseEntity.ok(resposta);
    }
}
