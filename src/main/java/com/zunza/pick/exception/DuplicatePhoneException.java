package com.zunza.pick.exception;

public class DuplicatePhoneException extends CustomException {

	private static final String MESSAGE = "이미 등록된 번호 입니다.";

	public DuplicatePhoneException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return 409;
	}
}
