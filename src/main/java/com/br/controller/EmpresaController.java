package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Empresa;
import com.br.repository.EmpresaRepository;

@RestController
@RequestMapping("/cempresa/")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRep;

    // LISTAR TODOS
    @GetMapping("/empresa")
    public List<Empresa> listar() {
        return empresaRep.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // CONSULTAR POR ID
    @GetMapping("/empresa/{id}")
    public ResponseEntity<Empresa> consultar(@PathVariable Long id) {
        Empresa empresa = empresaRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada: " + id));
        return ResponseEntity.ok(empresa);
    }

    // INSERIR NOVA EMPRESA
    @PostMapping("/empresa")
    public Empresa incluir(@RequestBody Empresa empresa) {
        return empresaRep.save(empresa);
    }

    // ALTERAR EMPRESA EXISTENTE
    @PutMapping("/empresa/{id}")
    public ResponseEntity<Empresa> alterar(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Empresa empresa = empresaRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada: " + id));

        empresa.setRazaoSocial(empresaAtualizada.getRazaoSocial());
        empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
        empresa.setCnpj(empresaAtualizada.getCnpj());
        empresa.setEmail(empresaAtualizada.getEmail());
        empresa.setTelefone(empresaAtualizada.getTelefone());
        empresa.setEndereco(empresaAtualizada.getEndereco()); // Endereço atualizado via cascade

        Empresa atualizado = empresaRep.save(empresa);
        return ResponseEntity.ok(atualizado);
    }

    // EXCLUIR EMPRESA
    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Empresa empresa = empresaRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada: " + id));

        empresaRep.delete(empresa);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Empresa excluída!", Boolean.TRUE);
        return ResponseEntity.ok(resposta);
    }
}
