package com.zunza.pick.member.entity;

import com.zunza.pick.common.BaseEntity;
import com.zunza.pick.member.constant.MemberType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
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

	@Column(unique = true, nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String address;

	@Column(unique = true, nullable = false)
	private String phone;

	@Enumerated(EnumType.STRING)
	private MemberType memberType;
}
