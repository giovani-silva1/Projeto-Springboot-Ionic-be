package br.com.springweb.controllers.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.springweb.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException objectNotFoundException,
			ServletRequest servletRequest) {
		StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(),
				"Dado não encontrado", objectNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}

}
