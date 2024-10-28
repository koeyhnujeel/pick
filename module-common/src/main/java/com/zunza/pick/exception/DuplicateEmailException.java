package com.zunza.pick.exception;

public class DuplicateEmailException extends CustomException {

	private static final String MESSAGE = "이미 사용 중인 이메일 입니다.";

	public DuplicateEmailException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return 409;
	}
}
