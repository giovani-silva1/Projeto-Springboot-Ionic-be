package br.com.springweb.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String sms) {
		super(sms);
	}

	public ObjectNotFoundException(String sms, Throwable throwable) {
		super(sms, throwable);
	}

}
