package com.zunza.pick.member.controller.dto;

import com.zunza.pick.member.controller.request.VerifyPhoneRequest;

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
