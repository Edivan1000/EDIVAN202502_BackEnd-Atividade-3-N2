package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Cliente;
import com.br.repository.ClienteRepository;

@RestController
@RequestMapping("/ccliente/")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRep;

	// LISTAR TODOS
	@GetMapping("/cliente")
	public List<Cliente> listar() {
		return clienteRep.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	// CONSULTAR POR ID
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> consultar(@PathVariable Long id) {
		Cliente cliente = clienteRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));
		return ResponseEntity.ok(cliente);
	}

	// INSERIR NOVO CLIENTE
	@PostMapping("/cliente")
	public Cliente incluir(@RequestBody Cliente cliente) {
		return clienteRep.save(cliente);
	}

	// ALTERAR CLIENTE EXISTENTE
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
		Cliente cliente = clienteRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

		cliente.setNome(clienteAtualizado.getNome());
		cliente.setCpfCnpj(clienteAtualizado.getCpfCnpj());
		cliente.setEmail(clienteAtualizado.getEmail());
		cliente.setTelefone(clienteAtualizado.getTelefone());
		cliente.setEndereco(clienteAtualizado.getEndereco()); // Endereço atualizado via cascade

		Cliente atualizado = clienteRep.save(cliente);
		return ResponseEntity.ok(atualizado);
	}

	// EXCLUIR CLIENTE
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
		Cliente cliente = clienteRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + id));

		clienteRep.delete(cliente);

		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Cliente excluído!", Boolean.TRUE);
		return ResponseEntity.ok(resposta);
	}
}
