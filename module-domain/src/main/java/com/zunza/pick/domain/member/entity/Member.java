package com.zunza.pick.domain.member.entity;

import com.zunza.pick.common.BaseEntity;
import com.zunza.pick.domain.member.constant.MemberType;
import com.zunza.pick.domain.member.dto.SignupDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String address;

	@Column(unique = true, nullable = false)
	private String phone;

	@Enumerated(EnumType.STRING)
	private MemberType memberType;

	@Builder
	private Member(String email, String password, String name, String nickname, String address, String phone,
		MemberType memberType) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.address = address;
		this.phone = phone;
		this.memberType = memberType;
	}

	public static Member from(SignupDto signupDto) {
		return Member.builder()
			.email(signupDto.getEmail())
			.password(signupDto.getPassword())
			.name(signupDto.getName())
			.nickname(signupDto.getNickname())
			.address(signupDto.getAddress())
			.phone(signupDto.getPhone())
			.memberType(signupDto.getMemberType())
			.build();
	}
}
