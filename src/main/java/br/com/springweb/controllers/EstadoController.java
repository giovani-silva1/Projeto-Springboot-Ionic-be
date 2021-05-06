package br.com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Estado;
import br.com.springweb.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<List<Estado>> listarTodos() {
		return ResponseEntity.ok().body(estadoService.listarEstados());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> encontrarEstadoPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(estadoService.encontrarEstadoById(id));
	}

}
