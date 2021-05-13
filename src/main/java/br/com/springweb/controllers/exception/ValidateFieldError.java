package br.com.springweb.controllers.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateFieldError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	
	private List<FieldError> errors = new ArrayList<>();
	
	public ValidateFieldError() {
		
	}

	public ValidateFieldError(int status, Long timeStamp, String error, String message) {
		super(status, timeStamp, error, message);
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void addErrors(String campo, String mensagem) {
		errors.add(new FieldError(campo, mensagem));
	}
	
	
	
	

}
