package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Produto;
import br.com.springweb.repositorys.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto listarProdutoPorId(Integer id) {
		Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
		return produtoEncontrado.get();
	}

}
