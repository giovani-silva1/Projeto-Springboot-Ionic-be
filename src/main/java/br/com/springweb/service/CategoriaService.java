package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Categoria;
import br.com.springweb.repositorys.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listarTodas() {
		List<Categoria> categoriaEncontradas = categoriaRepository.findAll();
		return categoriaEncontradas;
	}

	public Categoria encontrarCategoriaPorId(Long id) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		return categoriaEncontrada.get();

	}
	
	
	
}
