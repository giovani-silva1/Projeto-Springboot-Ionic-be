package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Cliente;
import br.com.springweb.repositorys.ClienteRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}

	public Cliente encontrarClientePorId(Integer id) {
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
		return clienteEncontrado.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado para o id informado:" + id + ", Tipo:" + Cliente.class));
	}
}
