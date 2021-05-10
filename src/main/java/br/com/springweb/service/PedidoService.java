package br.com.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springweb.entities.Pedido;
import br.com.springweb.repositorys.PedidoRepository;
import br.com.springweb.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public List<Pedido>listarPedidos(){
		return pedidoRepository.findAll();
	}
	
	public Pedido encontrarPedidoPorId(Integer id) {
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id);
		return pedidoEncontrado.orElseThrow( () -> new ObjectNotFoundException("Não foi encontrado o pedido solicitado:" + id + ", tipo:" + Pedido.class));
	}
	
}
