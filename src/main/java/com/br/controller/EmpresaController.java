package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.br.model.Empresa;
import com.br.repository.EmpresaRepository;

@RestController
@RequestMapping("/cempresa")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    // Listar todas as empresas
    @GetMapping("/empresa")
    public List<Empresa> listar() {
        // http://localhost:8080/cempresa/empresa (GET)
        return empresaRepository.findAll();
    }

    // Consultar empresa por ID
    @GetMapping("/empresa/{id}")
    public ResponseEntity<Empresa> consultar(@PathVariable Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));
        return ResponseEntity.ok(empresa);
    }

    // Incluir nova empresa
    @PostMapping("/empresa")
    public Empresa incluir(@RequestBody Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    // Alterar dados da empresa
    @PutMapping("/empresa/{id}")
    public ResponseEntity<Empresa> alterar(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));

        empresa.setNome(empresaAtualizada.getNome());
        empresa.setCnpj(empresaAtualizada.getCnpj());
        empresa.setClientes(empresaAtualizada.getClientes());

        Empresa atualizada = empresaRepository.save(empresa);
        return ResponseEntity.ok(atualizada);
    }

    // Excluir empresa
    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada."));

        empresaRepository.delete(empresa);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Empresa excluída!", true);
        return ResponseEntity.ok(resposta);
    }
}
