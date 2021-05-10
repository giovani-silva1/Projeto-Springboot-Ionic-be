package br.com.springweb.controllers.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.springweb.service.exception.IntegridadeBancoDeDados;
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
	
	@ExceptionHandler(IntegridadeBancoDeDados.class)
	public ResponseEntity<StandardError> DatabaseIntegrity(IntegridadeBancoDeDados integridadeBancoDeDados,
			ServletRequest servletRequest) {
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(),
				"Dado não pode ser excluido pois tem relacionamentos com outras operações no banco de dados", integridadeBancoDeDados.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

}
