package com.zunza.pick.member.mapper;

import com.zunza.pick.domain.member.dto.SignupDto;
import com.zunza.pick.domain.member.dto.VerifyEmailDto;
import com.zunza.pick.member.request.SignupRequest;
import com.zunza.pick.member.request.VerifyEmailRequest;

public interface MemberMapper {
	SignupDto toSignupDto(SignupRequest signupRequest);
	VerifyEmailDto toVerifyEmailDto(VerifyEmailRequest verifyEmailRequest);
}
