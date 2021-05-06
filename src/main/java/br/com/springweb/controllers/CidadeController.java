package br.com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Cidade;
import br.com.springweb.service.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	private ResponseEntity<List<Cidade>> ListarTodas() {
		return ResponseEntity.ok().body(cidadeService.listarTodos());
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<Cidade> listarCidadePorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(cidadeService.encontrarCidadePorId(id));
	}
}
