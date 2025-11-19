package com.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.br.model.Orcamento;
import com.br.repository.OrcamentoRepository;

@RestController
@RequestMapping("/corcamento")
@CrossOrigin(origins = "*")
public class OrcamentoController {

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	// LISTAR TODOS OS ORÇAMENTOS
	@GetMapping
	public List<Orcamento> listar() {
		return orcamentoRepository.findAll();
	}

	// CONSULTAR POR ID
	@GetMapping("/{id}")
	public Optional<Orcamento> buscarPorId(@PathVariable Long id) {
		return orcamentoRepository.findById(id);
	}

	// INSERIR NOVO ORÇAMENTO
	@PostMapping
	public Orcamento incluir(@RequestBody Orcamento orcamento) {
		// CascadeType.ALL já salva os itens
		return orcamentoRepository.save(orcamento);
	}

	// ALTERAR UM ORÇAMENTO EXISTENTE
	@PutMapping("/{id}")
	public Orcamento alterar(@PathVariable Long id, @RequestBody Orcamento orcamentoAtualizado) {
		return orcamentoRepository.findById(id).map(o -> {
			o.setDataOrcamento(orcamentoAtualizado.getDataOrcamento());
			o.setTotalOrcamento(orcamentoAtualizado.getTotalOrcamento());

			// Atualiza itens mantendo coerência bidirecional
			o.getItens().clear();
			if (orcamentoAtualizado.getItens() != null) {
				for (var item : orcamentoAtualizado.getItens()) {
					item.setOrcamento(o); // mantém o owner correto
					o.getItens().add(item);
				}
			}

			return orcamentoRepository.save(o);
		}).orElseThrow(() -> new RuntimeException("Orçamento não encontrado!"));
	}

	// EXCLUIR
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		if (!orcamentoRepository.existsById(id)) {
			throw new RuntimeException("Orçamento não encontrado para exclusão!");
		}
		orcamentoRepository.deleteById(id);
	}
}
