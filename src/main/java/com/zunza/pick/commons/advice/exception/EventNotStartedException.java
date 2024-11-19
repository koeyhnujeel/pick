package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class EventNotStartedException extends CustomException {

	private static final String MESSAGE = "이벤트 시작 전 입니다.";

	public EventNotStartedException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_FORBIDDEN;
	}
}
