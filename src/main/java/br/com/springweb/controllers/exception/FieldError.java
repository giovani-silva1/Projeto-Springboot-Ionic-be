package br.com.springweb.controllers.exception;

import java.io.Serializable;

public class FieldError implements Serializable{

	private static final long serialVersionUID = 1L;

	private String campo;
	private String mensagem;

	public FieldError() {

	}

	public FieldError(String campo, String message) {
		super();
		this.campo = campo;
		this.mensagem = message;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMessage() {
		return mensagem;
	}

	public void setMessage(String message) {
		this.mensagem = message;
	}

}
