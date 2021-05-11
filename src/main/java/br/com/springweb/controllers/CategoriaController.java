package br.com.springweb.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.dto.CategoriaDTO;
import br.com.springweb.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
		List<Categoria> categoriasEncontradas = categoriaService.listarTodas();
		List<CategoriaDTO> categoriaDTOs = categoriasEncontradas.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok(categoriaDTOs);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> listarCategoriasPaginadas(
			@RequestParam(name = "pagina" , defaultValue = "0") Integer pagina ,
			@RequestParam(name ="itensPorPagina", defaultValue = "24") Integer itensPorPagina,
			@RequestParam(name="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(name="campoOrdenacao", defaultValue = "nome") String campoOrdenacao
			) {
		
		Page<Categoria> categoriasEncontradas = categoriaService.listarCategorias(pagina,itensPorPagina,direcao,campoOrdenacao);
		Page<CategoriaDTO> categoriaDTOs = categoriasEncontradas.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok(categoriaDTOs);
	}
	
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(categoriaService.encontrarCategoriaPorId(id));
	}

	@PostMapping()
	public ResponseEntity<Categoria> adicionarCategoria(@RequestBody Categoria categoria) {
		Categoria categoriaNova = categoriaService.adicionarCategoria(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaNova.getId())
				.toUri();

		return ResponseEntity.created(uri).body(categoriaNova);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok().body(categoriaService.alterarCategoria(id, categoria));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
		categoriaService.deletarCategoria(id);
		return ResponseEntity.noContent().build();

	}
}
