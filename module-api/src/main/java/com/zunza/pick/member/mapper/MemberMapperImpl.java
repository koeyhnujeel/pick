package com.zunza.pick.member.mapper;

import org.springframework.stereotype.Component;

import com.zunza.pick.domain.member.dto.SignupDto;
import com.zunza.pick.domain.member.dto.VerifyEmailDto;
import com.zunza.pick.member.request.SignupRequest;
import com.zunza.pick.member.request.VerifyEmailRequest;

@Component
public class MemberMapperImpl implements MemberMapper {

	@Override
	public SignupDto toSignupDto(SignupRequest signupRequest) {
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

	@Override
	public VerifyEmailDto toVerifyEmailDto(VerifyEmailRequest verifyEmailRequest) {
		return new VerifyEmailDto(verifyEmailRequest.getEmail());
	}
}
