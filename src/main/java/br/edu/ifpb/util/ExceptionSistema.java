package br.edu.ifpb.util;

public class ExceptionSistema extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionSistema(String message) {
		super(message);
	}

	public ExceptionSistema(String message, Throwable cause) {
		super(message, cause);
	}
}
