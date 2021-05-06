package br.com.springweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Endereco;
import br.com.springweb.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Endereco> encontrarEnderecoPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(enderecoService.encontrarEnderecoPorId(id));
	}
}
