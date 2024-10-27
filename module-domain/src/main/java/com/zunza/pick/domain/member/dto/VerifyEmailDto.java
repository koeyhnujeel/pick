package com.zunza.pick.domain.member.dto;

import lombok.Getter;

@Getter
public class VerifyEmailDto {
	private String email;

	public VerifyEmailDto(String email) {
		this.email = email;
	}
}
