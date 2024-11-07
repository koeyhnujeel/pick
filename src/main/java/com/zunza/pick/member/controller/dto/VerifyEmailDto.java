package com.zunza.pick.member.controller.dto;

import com.zunza.pick.member.controller.request.VerifyEmailRequest;

import lombok.Getter;

@Getter
public class VerifyEmailDto {
	private String email;

	private VerifyEmailDto(String email) {
		this.email = email;
	}

	public static VerifyEmailDto from(VerifyEmailRequest verifyEmailRequest) {
		return new VerifyEmailDto(verifyEmailRequest.getEmail());
	}
}
