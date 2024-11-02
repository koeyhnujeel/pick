package com.zunza.pick.member.dto;

import com.zunza.pick.member.request.VerifyNicknameRequest;
import com.zunza.pick.member.request.VerifyPhoneRequest;

import lombok.Getter;

@Getter
public class VerifyPhoneDto {
	private String phone;

	private VerifyPhoneDto(String phone) {
		this.phone = phone;
	}

	public static VerifyPhoneDto from(VerifyPhoneRequest verifyPhoneRequest) {
		return new VerifyPhoneDto(verifyPhoneRequest.getPhone());
	}
}
