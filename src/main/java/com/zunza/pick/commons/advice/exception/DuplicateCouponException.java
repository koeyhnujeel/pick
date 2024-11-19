package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class DuplicateCouponException extends CustomException {

	private static final String MESSAGE = "이미 발급받은 쿠폰입니다.";

	public DuplicateCouponException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_CONFLICT;
	}
}
