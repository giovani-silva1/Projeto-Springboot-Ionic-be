package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Cliente;
import br.com.springweb.entities.dto.ClienteDto;
import br.com.springweb.repositorys.ClienteRepository;
import br.com.springweb.service.exception.IntegridadeBancoDeDados;
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
	
	
	public Page<Cliente> listarClientesPaginados(Integer pagina,Integer quantPorPagina,String direcao,String ordenarPeloCampo){
		PageRequest pageRequest = PageRequest.of(pagina, quantPorPagina,Direction.valueOf(direcao),ordenarPeloCampo);
		return clienteRepository.findAll(pageRequest);
	}
	
	public void deletarCliente(Integer id) {
		if(id == null) {
			throw new IllegalArgumentException("Id = " + null);
		}
		try {
			clienteRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new IntegridadeBancoDeDados("Não é possivel deletar este id, pois contem relação com outros dados, ID=" + id);
		}
	}
	
	
	public Cliente alterar(Integer id ,Cliente cliente) {
		Cliente clienteAlterado = clienteRepository.findById(id).orElseThrow( () -> new ObjectNotFoundException(" Id não encontrado" + id + ", tipo :" + Cliente.class));
		alteracaoCliente(clienteAlterado,cliente);
		return clienteAlterado;
	}
	
	
	private Cliente alteracaoCliente(Cliente clienteAlterado,Cliente cliente) {
		clienteAlterado.setNome(cliente.getNome());
		clienteAlterado.setEmail(cliente.getEmail());
		
		return clienteAlterado;
		
		
	}
	
	
	public Cliente fromDto(ClienteDto clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(),clienteDto.getEmail(), null, null);
	}
}
