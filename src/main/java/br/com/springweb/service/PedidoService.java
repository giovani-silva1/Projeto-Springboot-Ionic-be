package br.com.springweb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.ItemPedido;
import br.com.springweb.entities.PagamentoComBoleto;
import br.com.springweb.entities.Pedido;
import br.com.springweb.entities.enums.EstadoPagamento;
import br.com.springweb.repositorys.ClienteRepository;
import br.com.springweb.repositorys.ItemPedidoRepository;
import br.com.springweb.repositorys.PagamentoRepository;
import br.com.springweb.repositorys.PedidoRepository;
import br.com.springweb.repositorys.ProdutoRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido encontrarPedidoPorId(Integer id) {
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id);
		return pedidoEncontrado.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi encontrado o pedido solicitado:" + id + ", tipo:" + Pedido.class));
	}


	public Pedido salvarPedido(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.encontrarClientePorId(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.definirDataPagamento(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.listarProdutoPorId(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj.toString());
		return obj;
	}
}
