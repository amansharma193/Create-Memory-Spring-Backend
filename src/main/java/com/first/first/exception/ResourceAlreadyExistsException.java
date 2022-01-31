package com.first.first.exception;

public class ResourceAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
