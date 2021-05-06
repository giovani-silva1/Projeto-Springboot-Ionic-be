package br.com.springweb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.Cidade;
import br.com.springweb.entities.Estado;
import br.com.springweb.entities.Produto;
import br.com.springweb.repositorys.CategoriaRepository;
import br.com.springweb.repositorys.CidadeRepository;
import br.com.springweb.repositorys.EstadoRepository;
import br.com.springweb.repositorys.ProdutoRepository;

@Configuration
@Profile(value = "test")
public class Configtest implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritório");

		Produto produto = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria.getProdutos().addAll(Arrays.asList(produto, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto));
		produto.getCategorias().addAll(Arrays.asList(categoria));
		produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria));

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

	}

}
