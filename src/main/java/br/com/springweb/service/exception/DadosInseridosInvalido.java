package br.com.springweb.service.exception;

public class DadosInseridosInvalido extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public DadosInseridosInvalido(String sms) {
		super(sms);
	}
	
}
