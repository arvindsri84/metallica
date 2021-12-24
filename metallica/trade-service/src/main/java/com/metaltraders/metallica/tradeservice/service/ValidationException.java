package com.metaltraders.metallica.tradeservice.service;

public class ValidationException extends RuntimeException {

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 7379392553488399268L;

	public ValidationException(String message) {
		super(message);
	}

}
