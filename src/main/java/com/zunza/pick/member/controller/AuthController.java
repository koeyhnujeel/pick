package com.zunza.pick.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.member.dto.SignupDto;
import com.zunza.pick.member.dto.RefreshTokenDto;
import com.zunza.pick.member.dto.VerifyEmailDto;
import com.zunza.pick.member.dto.VerifyNicknameDto;
import com.zunza.pick.member.dto.VerifyPhoneDto;
import com.zunza.pick.member.request.RefreshTokenRequest;
import com.zunza.pick.member.request.VerifyNicknameRequest;
import com.zunza.pick.member.request.VerifyPhoneRequest;
import com.zunza.pick.member.response.TokenResponse;
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

	@PostMapping("/api/auth/signup/email/verify-duplicate")
	public ResponseEntity<Void> verifyDuplicateEmail(@Valid @RequestBody VerifyEmailRequest verifyEmailRequest) {
		VerifyEmailDto verifyEmailDto = VerifyEmailDto.from(verifyEmailRequest);
		authService.verifyDuplicateEmail(verifyEmailDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/api/auth/signup/nickname/verify-duplicate")
	public ResponseEntity<Void> verifyDuplicateNickname(@Valid @RequestBody VerifyNicknameRequest verifyNicknameRequest) {
		VerifyNicknameDto verifyNicknameDto = VerifyNicknameDto.from(verifyNicknameRequest);
		authService.verifyDuplicateNickname(verifyNicknameDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/api/auth/signup/phone/verify-duplicate")
	public ResponseEntity<Void> verifyDuplicatePhone(@Valid @RequestBody VerifyPhoneRequest verifyPhoneRequest) {
		VerifyPhoneDto verifyPhoneDto = VerifyPhoneDto.from(verifyPhoneRequest);
		authService.verifyDuplicatePhone(verifyPhoneDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/api/auth/token/refresh")
	public ResponseEntity<TokenResponse> tokenRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		RefreshTokenDto refreshTokenDto = RefreshTokenDto.from(refreshTokenRequest);
		TokenResponse tokenResponse = authService.tokenRefresh(refreshTokenDto);
		return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
	}
}
