package com.zunza.pick.commons.advice.exception;

import jakarta.servlet.http.HttpServletResponse;

public class CouponOutOfStockException extends CustomException {

	private static final String MESSAGE = "쿠폰이 모두 소진되었습니다.";

	public CouponOutOfStockException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_BAD_REQUEST;
	}
}
