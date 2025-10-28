package com.br.service;

import com.br.model.Cliente;
import com.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Retorna todos os clientes do banco de dados.
     * Retorna uma lista vazia ([]), se nenhum cliente for encontrado.
     */
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Salva ou atualiza um cliente no banco de dados.
     */
    public Cliente salvarCliente(Cliente cliente) {
        // Você pode adicionar lógica de negócio/validação aqui antes de salvar
        return clienteRepository.save(cliente);
    }
    
    /**
     * Busca um cliente pelo seu ID.
     */
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    // Método para Excluir (Adicionado para completar o CRUD Service)
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}