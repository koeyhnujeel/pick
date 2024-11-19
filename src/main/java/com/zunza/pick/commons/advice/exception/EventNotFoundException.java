package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class EventNotFoundException extends CustomException {

	private static final String MESSAGE = "존재하지 않는 이벤트입니다.";

	public EventNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
}
