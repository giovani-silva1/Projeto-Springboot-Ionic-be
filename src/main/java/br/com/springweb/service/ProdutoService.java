package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.Produto;
import br.com.springweb.repositorys.CategoriaRepository;
import br.com.springweb.repositorys.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto listarProdutoPorId(Integer id) {
		Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
		return produtoEncontrado.get();
	}

	public Page<Produto> listarProdutosPorNomeEmCategorias(String nome, List<Integer> ids, Integer pagina,
			Integer quantItensPorPagina, String direcao, String campoOrdenacao) {
		PageRequest pageRequest = PageRequest.of(pagina, quantItensPorPagina, Direction.valueOf(direcao),
				campoOrdenacao);
		List<Categoria> categoriasEncontradas = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categoriasEncontradas, pageRequest);
	}

}
