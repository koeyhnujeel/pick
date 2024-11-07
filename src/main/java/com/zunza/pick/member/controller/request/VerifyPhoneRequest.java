package com.zunza.pick.member.controller.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyPhoneRequest {

	@NotBlank(message = "핸드폰 번호를 입력해 주세요.")
	private String phone;
}
