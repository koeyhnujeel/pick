package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class EventExpiredException extends CustomException {

	private static final String MESSAGE = "이벤트가 종료되었습니다.";

	public EventExpiredException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_GONE;
	}
}
