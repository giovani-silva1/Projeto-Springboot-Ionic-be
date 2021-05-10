package br.com.springweb.service.exception;

public class IntegridadeBancoDeDados extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeBancoDeDados(String sms) {
		super(sms);
	}
	
	public IntegridadeBancoDeDados(String sms,Throwable throwable) {
		super(sms,throwable);
	}
}
