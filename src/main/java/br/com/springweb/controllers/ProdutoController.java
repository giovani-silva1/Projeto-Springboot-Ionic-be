package br.com.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springweb.entities.Produto;
import br.com.springweb.entities.dto.ProdutoDTO;
import br.com.springweb.service.ProdutoService;
import br.com.springweb.service.utils.ConvertListCategoriasInteger;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity.ok().body(produtoService.listarProdutos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(produtoService.listarProdutoPorId(id));
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProdutoDTO>> encontrarProdutosPorNomeNasCategorias(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "itensPorPagina", defaultValue = "24") Integer itensPorPagina,
			@RequestParam(value = "ordenacao", defaultValue = "ASC") String ordenacao,
			@RequestParam(value = "campoOrdenacao", defaultValue = "nome") String nomeCampo) {
		List<Integer> categoriasEncontradas = ConvertListCategoriasInteger.convertList(categorias);
		Page<Produto> produtosEncontrados = produtoService.listarProdutosPorNomeEmCategorias(nome,
				categoriasEncontradas, pagina, itensPorPagina, ordenacao, nomeCampo);
		Page<ProdutoDTO> produtosDTOencontrados = produtosEncontrados.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(produtosDTOencontrados);
	}
}
