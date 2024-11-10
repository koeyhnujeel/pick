package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class InvalidRefreshTokenException extends CustomException {

	private static final String MESSAGE = "유효하지 않은 Refresh 토큰입니다.";

	public InvalidRefreshTokenException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_UNAUTHORIZED;
	}
}
