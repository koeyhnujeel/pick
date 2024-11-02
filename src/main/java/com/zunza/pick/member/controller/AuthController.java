package com.zunza.pick.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.member.dto.SignupDto;
import com.zunza.pick.member.dto.VerifyEmailDto;
import com.zunza.pick.member.service.AuthService;
import com.zunza.pick.member.request.SignupRequest;
import com.zunza.pick.member.request.VerifyEmailRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/api/auth/signup")
	public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest signupRequest) {
		SignupDto signupDto = SignupDto.from(signupRequest);
		authService.signup(signupDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("api/auth/signup/email/verify-duplicate")
	public ResponseEntity<Void> verifyDuplicateEmail(@Valid @RequestBody VerifyEmailRequest verifyEmailRequest) {
		VerifyEmailDto verifyEmailDto = VerifyEmailDto.from(verifyEmailRequest);
		authService.verifyDuplicateEmail(verifyEmailDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
