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
import com.br.model.*;
import com.br.repository.CertificadoDigitalRepository;

@RestController
@RequestMapping("/ccertificadodigital")
@CrossOrigin(origins="*")


public class CertificadoDigitalController {
	
	//Cria o repositorio JPA para ser usado aqui no controlador
	@Autowired
	private CertificadoDigitalRepository autorep;

	@GetMapping("/certificadodigital") //Indica que esse será o nome do endereço a ser chamado
	public List<CertificadoDigital> listar(){
		
		//para chamar o "listar", o endereço completo deverá ser:
		// http://localhost:8080/ccertificadodigital/certificadodigital -- usando o protocolo http, método GET
		//Traz a lista de produtos escolhidos pelo cliente
		
		
		return autorep.findAll();
		
		
	}
	
	
	//Consultar (cliente realiza a consulta de determinado produto escolhido por ele)
	@GetMapping("/certificadodigital/{id}")
	public ResponseEntity<CertificadoDigital> consultar(@PathVariable Long id) {
	
		CertificadoDigital auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Certificado Digital nao encontrado."));
		
	return ResponseEntity.ok(auto);
	}
	
	//Incluir (cliente adiciona mais produtos para comprar)
	//http://localhost:8080/ccertificadodigital/certificadodigital -- usando o protocolo http, método POST
	@PostMapping("/certificadodigital")
	public CertificadoDigital incluir(@RequestBody CertificadoDigital certificadodigital) {
		
		return autorep.save(certificadodigital);
	}
	
	//Excluir (cliente exclui produto do seu pedido)
	@DeleteMapping("/certificadodigital/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
		
		CertificadoDigital auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Certificado Digital nao encontrado."));
		
		autorep.delete(auto);
		
		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Certificado Digital Excluido!", true);
		return ResponseEntity.ok(resposta);
	}
	
	
	//Alterar (cliente realiza alteração do item de algum produto escolhido)
	@PutMapping("/certificadodigital/{id}")
	public ResponseEntity<CertificadoDigital> alterar(@PathVariable Long id,@RequestBody CertificadoDigital certificadodigital) {
	
		CertificadoDigital auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Certificado Digital nao encontrado."));
		
		auto.setId(certificadodigital.getId());
		auto.setNome(certificadodigital.getNome());
		auto.setAtivo(certificadodigital.isAtivo());
		
		CertificadoDigital atualizado = autorep.save(auto);
		
		return ResponseEntity.ok(atualizado);
	}
	
	
	
	

}