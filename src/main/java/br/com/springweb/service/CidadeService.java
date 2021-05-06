package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Cidade;
import br.com.springweb.repositorys.CidadeRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;
@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listarTodos() {
		return cidadeRepository.findAll();
	}

	public Cidade encontrarCidadePorId(Integer id) {
		Optional<Cidade> cidadesEncontrada = cidadeRepository.findById(id);
		return cidadesEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Registro não encontrada pelo id informado: " + id + ", Tipo:" + Cidade.class));
	}
}
