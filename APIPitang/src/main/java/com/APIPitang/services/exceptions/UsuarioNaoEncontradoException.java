package com.APIPitang.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6039142103211612454L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
