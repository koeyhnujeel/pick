package com.zunza.pick.exception;

public class DuplicateNicknameException extends CustomException {

	private static final String MESSAGE = "이미 사용 중인 닉네임 입니다.";

	public DuplicateNicknameException() {
		super(MESSAGE);
	}

	@Override
	public int getStatusCode() {
		return 409;
	}
}
