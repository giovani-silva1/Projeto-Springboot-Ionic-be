package br.com.springweb.controllers.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private Long timeStamp;
	private String error;
	private String message;
	
	
	public StandardError() {
		
	}


	public StandardError(int status, Long timeStamp, String error, String message) {
		this.status = status;
		this.timeStamp = timeStamp;
		this.error = error;
		this.message = message;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
