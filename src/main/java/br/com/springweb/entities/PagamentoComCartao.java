package br.com.springweb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.springweb.entities.enums.EstadoPagamento;

@Entity
@Table(name = "tb_pagamento_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
