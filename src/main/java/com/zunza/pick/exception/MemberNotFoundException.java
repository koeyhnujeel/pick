package com.zunza.pick.exception;

import jakarta.servlet.http.HttpServletResponse;

public class MemberNotFoundException extends CustomException {

	private static final String MESSAGE = "존재하지 않는 회원입니다.";

	public MemberNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
}
