package br.com.springweb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.Cidade;
import br.com.springweb.entities.Cliente;
import br.com.springweb.entities.Endereco;
import br.com.springweb.entities.Estado;
import br.com.springweb.entities.ItemPedido;
import br.com.springweb.entities.Pagamento;
import br.com.springweb.entities.PagamentoComBoleto;
import br.com.springweb.entities.PagamentoComCartao;
import br.com.springweb.entities.Pedido;
import br.com.springweb.entities.Produto;
import br.com.springweb.entities.enums.EstadoPagamento;
import br.com.springweb.entities.enums.TipoCliente;
import br.com.springweb.repositorys.CategoriaRepository;
import br.com.springweb.repositorys.CidadeRepository;
import br.com.springweb.repositorys.ClienteRepository;
import br.com.springweb.repositorys.EnderecoRepository;
import br.com.springweb.repositorys.EstadoRepository;
import br.com.springweb.repositorys.ItemPedidoRepository;
import br.com.springweb.repositorys.PagamentoRepository;
import br.com.springweb.repositorys.PedidoRepository;
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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "CAMA MESA E BANHO");
		Categoria categoria4 = new Categoria(null, "SOFTWARES");
		Categoria categoria5 = new Categoria(null, "JARDINAGEM");
		Categoria categoria6 = new Categoria(null, "FRUTAS");
		Categoria categoria7 = new Categoria(null, "JOGOS ONLINE");
		Categoria categoria8 = new Categoria(null, "ESPORTES");
		Categoria categoria9 = new Categoria(null, "CARROS ESPORTIVOS");
		Categoria categoria10 = new Categoria(null, "LIVROS");
		Categoria categoria11 = new Categoria(null, "FERRAMENTAS");

		Produto produto = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria.getProdutos().addAll(Arrays.asList(produto, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto));
		produto.getCategorias().addAll(Arrays.asList(categoria));
		produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria));

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7, categoria8, categoria9, categoria10, categoria11));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco endereco = new Endereco(null, "Rua Flores", "300", "Apto203", "Jardim", "38220834", cidade1, cliente);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala800", "Centro", "38777012", cidade2,
				cliente);

		clienteRepository.save(cliente);
		enderecoRepository.saveAll(Arrays.asList(endereco, endereco2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		Pedido pedido = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente, endereco);
		Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente, endereco);
		Pagamento pagamento = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				simpleDateFormat2.parse("20/10/2017"), null);
		pedido.setPagamento(pagamento);
		pedido2.setPagamento(pagamento2);

		pedidoRepository.saveAll(Arrays.asList(pedido, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento, pagamento2));

		cliente.setPedidos(Arrays.asList(pedido, pedido2));

		ItemPedido itemPedido = new ItemPedido(pedido, produto, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido2, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);

		pedido.getItens().addAll(Arrays.asList(itemPedido, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		produto.getItens().addAll(Arrays.asList(itemPedido));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido, itemPedido2, itemPedido3));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));
		pedidoRepository.saveAll(Arrays.asList(pedido, pedido2));

	}

}
