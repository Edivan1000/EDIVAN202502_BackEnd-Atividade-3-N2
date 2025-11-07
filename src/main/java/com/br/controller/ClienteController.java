package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Cliente;
import com.br.repository.ClienteRepository;

@RestController
@RequestMapping("/ccliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    // Injeção do repositório JPA
    @Autowired
    private ClienteRepository autorep;

    // Listar todos os clientes
    @GetMapping("/cliente")
    public List<Cliente> listar() {
        // Exemplo de acesso: http://localhost:8080/ccliente/cliente
        return autorep.findAll();
    }

    // Consultar cliente por ID
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> consultar(@PathVariable Long id) {
        Cliente auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        return ResponseEntity.ok(auto);
    }

    // Incluir novo cliente
    @PostMapping("/cliente")
    public Cliente incluir(@RequestBody Cliente cliente) {
        return autorep.save(cliente);
    }

    // Excluir cliente
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Cliente auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        autorep.delete(auto);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Cliente excluído!", true);
        return ResponseEntity.ok(resposta);
    }

    // Alterar cliente existente
    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> alterar(@PathVariable Long id, @RequestBody Cliente cliente) {

        Cliente auto = autorep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        // Atualiza os campos principais do cliente
        auto.setNome(cliente.getNome());
        auto.setEmail(cliente.getEmail());
        auto.setTelefone(cliente.getTelefone());
        auto.setCpfCnpj(cliente.getCpfCnpj());
        auto.setEmpresas(cliente.getEmpresas());
        auto.setOrcamentos(cliente.getOrcamentos());

        Cliente atualizado = autorep.save(auto);
        return ResponseEntity.ok(atualizado);
    }
}
