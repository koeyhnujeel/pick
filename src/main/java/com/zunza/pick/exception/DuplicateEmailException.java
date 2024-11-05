package com.zunza.pick.exception;

import jakarta.servlet.http.HttpServletResponse;

public class DuplicateEmailException extends CustomException {

	private static final String MESSAGE = "이미 사용 중인 이메일 입니다.";

	public DuplicateEmailException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_CONFLICT;
	}
}
