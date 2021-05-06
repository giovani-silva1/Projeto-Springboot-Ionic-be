package br.com.springweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Endereco;
import br.com.springweb.repositorys.EnderecoRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco encontrarEnderecoPorId(Integer id) {
		Optional<Endereco> enderecoEncontrado = enderecoRepository.findById(id);
		return enderecoEncontrado.orElseThrow( ()-> new ObjectNotFoundException("Endereco encontrado pelo id informado :" + id + ", tipo " + Endereco.class));
	}
}
