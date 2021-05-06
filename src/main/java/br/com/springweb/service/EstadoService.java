package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Estado;
import br.com.springweb.repositorys.EstadoRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listarEstados() {
		return estadoRepository.findAll();
	}

	public Estado encontrarEstadoById(Long id) {
		Optional<Estado> estadoEncontrado = estadoRepository.findById(id);
		return estadoEncontrado.orElseThrow(() -> new ObjectNotFoundException(
				"Estado não encontrado para o id solicitado:" + id + ", tipo:" + Estado.class));
	}
}
