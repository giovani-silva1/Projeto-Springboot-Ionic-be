package br.com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Cliente;
import br.com.springweb.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		return ResponseEntity.ok().body(clienteService.listarCliente());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> encontrarClientePorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(clienteService.encontrarClientePorId(id));
	}
}
