package com.br.vendas.exception;

public class ClienteRestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736549529679034331L;

	public ClienteRestException(String message) {
        super(message);
    }
}
