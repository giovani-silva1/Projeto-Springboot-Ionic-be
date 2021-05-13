package br.com.springweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Cliente;
import br.com.springweb.entities.dto.ClienteDto;
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
	public ResponseEntity<Cliente> encontrarClientePorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(clienteService.encontrarClientePorId(id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,
			@Valid @RequestBody ClienteDto clienteDto) {
		Cliente cliente = clienteService.fromDto(clienteDto);
		return ResponseEntity.ok().body(clienteService.alterar(id, cliente));
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDto>> listarClientesPaginados(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(name = "itensPagina", defaultValue = "24") Integer itensPagina,
			@RequestParam(name = "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(name = "ordenarPor", defaultValue = "nome") String nome) {

		Page<Cliente> clientesPaginados = clienteService.listarClientesPaginados( pagina,itensPagina,direcao, nome);
		Page<ClienteDto> clienteDtoPaginados = clientesPaginados.map(cliente -> new ClienteDto(cliente));
		return ResponseEntity.ok().body(clienteDtoPaginados);
	}
}
