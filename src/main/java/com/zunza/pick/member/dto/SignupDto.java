package com.zunza.pick.member.dto;

import com.zunza.pick.member.constant.MemberType;
import com.zunza.pick.member.request.SignupRequest;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupDto {
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String address;
	private String phone;
	private MemberType memberType;

	@Builder
	private SignupDto(String email, String password, String name, String nickname, String address, String phone,
		MemberType memberType) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.address = address;
		this.phone = phone;
		this.memberType = memberType;
	}

	public static SignupDto from(SignupRequest signupRequest) {
		return SignupDto.builder()
			.email(signupRequest.getEmail())
			.password(signupRequest.getPassword())
			.name(signupRequest.getName())
			.nickname(signupRequest.getNickname())
			.address(signupRequest.getAddress())
			.phone(signupRequest.getPhone())
			.memberType(signupRequest.getMemberType())
			.build();
	}

	public void setEncodedPassword(String encodedPassword) {
		this.password = encodedPassword;
	}
}
