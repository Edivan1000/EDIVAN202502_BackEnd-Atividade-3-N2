package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.br.model.CategoriaCertificado;
import com.br.repository.CategoriaCertificadoRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/ccategoria/")
@RestController
public class CategoriaCertificadoController {

	@Autowired
	private CategoriaCertificadoRepository cRep;

	// Listar
	@GetMapping("/categoria")
	public List<CategoriaCertificado> listar() {
		return this.cRep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
	}

	// Consultar
	@GetMapping("/categoria/{id}")
	public ResponseEntity<CategoriaCertificado> consultar(@PathVariable Long id) {

		CategoriaCertificado cat = this.cRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + id));

		return ResponseEntity.ok(cat);
	}

	// Inserir
	@PostMapping("/categoria")
	public CategoriaCertificado inserir(@RequestBody CategoriaCertificado categoria) {
		return this.cRep.save(categoria);
	}

	// Alterar
	@PutMapping("/categoria/{id}")
	public ResponseEntity<CategoriaCertificado> alterar(@PathVariable Long id,
			@RequestBody CategoriaCertificado categoria) {

		CategoriaCertificado catLoc = this.cRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + id));

		catLoc.setCodigo(categoria.getCodigo());
		catLoc.setNome(categoria.getNome());

		CategoriaCertificado atualizado = this.cRep.save(catLoc);

		return ResponseEntity.ok(atualizado);
	}

	// Excluir
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {

		CategoriaCertificado cat = this.cRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + id));

		cRep.delete(cat);

		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Categoria excluída!", Boolean.TRUE);
		return ResponseEntity.ok(resposta);
	}
}
