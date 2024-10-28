package com.zunza.pick.exception;

public abstract class CustomException extends RuntimeException {

	public CustomException(String message) {
		super(message);
	}

	public abstract int getStatusCode();
}
