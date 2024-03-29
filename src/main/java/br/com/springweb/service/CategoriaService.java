package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.AuxiliaryDatabaseObjectBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.dto.CategoriaDTO;
import br.com.springweb.repositorys.CategoriaRepository;
import br.com.springweb.service.exception.IntegridadeBancoDeDados;
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

	public CategoriaDTO alterarCategoria(Integer id, Categoria categoria) {
		Categoria categoriaEncontrada = categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
		alterarCategoria(categoriaEncontrada, categoria);
		categoriaEncontrada = categoriaRepository.save(categoriaEncontrada);
		return fromEntity(categoriaEncontrada);
		
	}

	private void alterarCategoria(Categoria categoriaEncontrada, Categoria categoria) {
		categoriaEncontrada.setNome(categoria.getNome());
	}

	public void deletarCategoria(Integer id) {
		encontrarCategoriaPorId(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (RuntimeException e) {
			throw new IntegridadeBancoDeDados("Não foi possivel realizar a exclusão.");
		}

	}

	public Page<Categoria> listarCategorias(Integer numeroPagina, Integer itensPorPagina, String direcao,
			String campoOrdenado) {
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.valueOf(direcao),
				campoOrdenado);
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria fromDto(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	public CategoriaDTO fromEntity (Categoria categoria) {
		return new CategoriaDTO(categoria);
	}

	

}
