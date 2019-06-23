package com.br.vendas.exception;

import java.util.Date;

public class ErrorMessage {

	private String message;
	private Date timestamp;
	private String status;
	
	public ErrorMessage(Date timestamp, String message, String status) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
