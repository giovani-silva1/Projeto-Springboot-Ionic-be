package br.com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Pedido;
import br.com.springweb.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> listarTodos() {
		return ResponseEntity.ok().body(pedidoService.listarPedidos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> encontrarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(pedidoService.encontrarPedidoPorId(id));
	}

}
