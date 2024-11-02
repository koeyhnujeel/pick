package com.zunza.pick.member.dto;

import com.zunza.pick.member.request.VerifyEmailRequest;
import com.zunza.pick.member.request.VerifyNicknameRequest;

import lombok.Getter;

@Getter
public class VerifyNicknameDto {
	private String nickname;

	private VerifyNicknameDto(String nickname) {
		this.nickname = nickname;
	}

	public static VerifyNicknameDto from(VerifyNicknameRequest verifyNicknameRequest) {
		return new VerifyNicknameDto(verifyNicknameRequest.getNickname());
	}
}
