package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class CouponNotFoundException extends CustomException {

	private static final String MESSAGE = "존재하지 않는 쿠폰입니다.";

	public CouponNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
}
