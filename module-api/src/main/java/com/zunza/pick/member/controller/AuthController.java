package com.zunza.pick.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.domain.member.dto.SignupDto;
import com.zunza.pick.domain.member.dto.VerifyEmailDto;
import com.zunza.pick.domain.member.service.AuthService;
import com.zunza.pick.member.mapper.MemberMapper;
import com.zunza.pick.member.request.SignupRequest;
import com.zunza.pick.member.request.VerifyEmailRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final MemberMapper mapper;

	@PostMapping("/api/auth/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void signup(@Valid @RequestBody SignupRequest signupRequest) {
		SignupDto signupDto = mapper.toSignupDto(signupRequest);
		authService.signup(signupDto);
	}

	@PostMapping("api/auth/signup/email/verify-duplicate")
	@ResponseStatus(HttpStatus.OK)
	public void verifyDuplicateEmail(@Valid @RequestBody VerifyEmailRequest verifyEmailRequest) {
		VerifyEmailDto verifyEmailDto = mapper.toVerifyEmailDto(verifyEmailRequest);
		authService.verifyDuplicateEmail(verifyEmailDto);
	}
}
