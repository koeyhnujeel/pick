package com.zunza.pick.domain.member.dto;

import com.zunza.pick.domain.member.constant.MemberType;

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
}
