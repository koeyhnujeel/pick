package com.zunza.pick.commons.advice.exception;

public abstract class CustomException extends RuntimeException {

	public CustomException(String message) {
		super(message);
	}

	public abstract int getStatusCode();
}
