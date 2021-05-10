package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Categoria;
import br.com.springweb.repositorys.CategoriaRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listarTodas() {
		List<Categoria> categoriaEncontradas = categoriaRepository.findAll();
		return categoriaEncontradas;
	}

	public Categoria encontrarCategoriaPorId(Integer id) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		return categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado para o Id:" + id + ", Tipo:" + Categoria.class));

	}

	public Categoria adicionarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria alterarCategoria(Integer id, Categoria categoria) {
		Categoria categoriaEncontrada = categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
		alterarCategoria(categoriaEncontrada, categoria);
		return categoriaRepository.save(categoriaEncontrada);
	}

	private void alterarCategoria(Categoria categoriaEncontrada, Categoria categoria) {
		categoriaEncontrada.setNome(categoria.getNome());
	}

}
