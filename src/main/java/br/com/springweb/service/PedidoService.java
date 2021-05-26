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

	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido encontrarPedidoPorId(Integer id) {
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id);
		return pedidoEncontrado.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi encontrado o pedido solicitado:" + id + ", tipo:" + Pedido.class));
	}

/*	@Transactional
	public Pedido salvarPedido(Pedido novoPedido) {
		novoPedido.setId(null);
		novoPedido.setInstante(new Date());
		novoPedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		novoPedido.getPagamento().setPedido(novoPedido);
		if (novoPedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) novoPedido.getPagamento();
			boletoService.definirDataPagamento(pagamentoComBoleto, novoPedido.getInstante());
		}

		novoPedido = pedidoRepository.save(novoPedido);
		pagamentoRepository.save(novoPedido.getPagamento());

		for (ItemPedido itens : novoPedido.getItens()) {
			itens.setDesconto(0.0);
			itens.setPreco(produtoRepository.findById(itens.getProduto().getId()).orElseThrow().getPreco());
			itens.setPedido(novoPedido);
		}
		itemPedidoRepository.saveAll(novoPedido.getItens());
		return novoPedido;
	}
*/
	
	@Transactional
	public Pedido salvarPedido(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
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
			ip.setPreco(produtoService.listarProdutoPorId(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
