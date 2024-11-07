package com.zunza.pick.member.controller.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyNicknameRequest {

	@NotBlank(message = "닉네임을 입력해 주세요.")
	@Length(min = 2, max = 10, message = "닉네임은 2자~10자로 설정해 주세요.")
	private String nickname;
}
