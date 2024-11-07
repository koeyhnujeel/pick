package com.zunza.pick.member.controller.dto;

import com.zunza.pick.member.controller.request.VerifyNicknameRequest;

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
