package br.com.springweb.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.springweb.entities.PagamentoComBoleto;

@Service
public class BoletoService {

	public void definirDataPagamento(PagamentoComBoleto pagamentoComBoleto, Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataVencimento(calendar.getTime());
	}
}
