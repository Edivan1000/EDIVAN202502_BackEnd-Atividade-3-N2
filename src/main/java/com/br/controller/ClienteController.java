package com.br.controller;

import com.br.model.Cliente;
import com.br.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // POST /api/clientes
    // Incluir um novo cliente (ou atualizar se o ID vier preenchido)
    @PostMapping
    public ResponseEntity<Cliente> incluirCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }
    
    // GET /api/clientes/1
    // Consultar (Buscar por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/clientes
    // CORREÇÃO: Usando ResponseEntity<List<Cliente>> para garantir o status 200 e o corpo JSON [].
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        // Assume que listarClientes() retorna List<Cliente> e não null.
        List<Cliente> clientes = clienteService.listarClientes();
        
        // Se a lista for nula (improvável se o Service estiver correto), retorna uma lista vazia.
        if (clientes == null) {
            clientes = Collections.emptyList();
        }
        
        // Retorna a lista com status 200 OK. Isso garante o corpo JSON: [] se vazio.
        return ResponseEntity.ok(clientes); 
    }
    
    // Implementar métodos PUT (Alterar) e DELETE (Excluir) para completar o CRUD
}