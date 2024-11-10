package com.zunza.pick.member.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zunza.pick.commons.advice.exception.DuplicateNicknameException;
import com.zunza.pick.commons.advice.exception.DuplicatePhoneException;
import com.zunza.pick.commons.advice.exception.InvalidRefreshTokenException;
import com.zunza.pick.commons.advice.exception.MemberNotFoundException;
import com.zunza.pick.member.entity.MemberType;
import com.zunza.pick.member.controller.dto.SignupDto;
import com.zunza.pick.member.controller.dto.RefreshTokenDto;
import com.zunza.pick.member.controller.dto.VerifyEmailDto;
import com.zunza.pick.member.controller.dto.VerifyNicknameDto;
import com.zunza.pick.member.controller.dto.VerifyPhoneDto;
import com.zunza.pick.member.entity.Member;
import com.zunza.pick.member.repository.MemberRepository;
import com.zunza.pick.commons.advice.exception.DuplicateEmailException;
import com.zunza.pick.member.repository.TokenRedisRepository;
import com.zunza.pick.member.controller.response.TokenResponse;
import com.zunza.pick.commons.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final TokenRedisRepository tokenRedisRepository;

	public void signup(SignupDto signupDto) {
		signupDto.setEncodedPassword(passwordEncoder.encode(signupDto.getPassword()));
		Member member = Member.from(signupDto);
		memberRepository.save(member);
	}

	public void verifyDuplicateEmail(VerifyEmailDto verifyEmailDto) {
		Boolean duplicateEmail = memberRepository.isDuplicateEmail(verifyEmailDto.getEmail());
		if (duplicateEmail) {
			throw new DuplicateEmailException();
		}
	}

	public void verifyDuplicateNickname(VerifyNicknameDto verifyNicknameDto) {
		Boolean duplicateNickname = memberRepository.isDuplicateNickname(verifyNicknameDto.getNickname());
		if (duplicateNickname) {
			throw new DuplicateNicknameException();
		}
	}

	public void verifyDuplicatePhone(VerifyPhoneDto verifyPhoneDto) {
		Boolean duplicatePhone = memberRepository.isDuplicatePhone(verifyPhoneDto.getPhone());
		if (duplicatePhone) {
			throw new DuplicatePhoneException();
		}
	}

	public TokenResponse tokenRefresh(RefreshTokenDto refreshTokenDto) {
		String refreshToken = refreshTokenDto.getRefreshToken();
		if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
			throw new InvalidRefreshTokenException();
		}

		String memberId = jwtTokenProvider.getSubject(refreshToken);
		String savedRefreshToken = tokenRedisRepository.findRefreshTokenById(memberId);

		if (!refreshToken.equals(savedRefreshToken)) {
			throw new InvalidRefreshTokenException();
		}

		Member member = memberRepository.findById(Long.parseLong(memberId))
			.orElseThrow(MemberNotFoundException::new);

		String role = getRole(member.getMemberType());
		String newAccessToken = jwtTokenProvider.createAccessToken(memberId, List.of(role));
		String newRefreshToken = jwtTokenProvider.createRefreshToken(memberId);

		tokenRedisRepository.saveRefreshToken(memberId, newRefreshToken);

		return new TokenResponse(newAccessToken, newRefreshToken);
	}

	private String getRole(MemberType memberType) {
		return memberType.equals(MemberType.CUSTOMER) ? "ROLE_CUSTOMER" :
			memberType.equals(MemberType.SELLER) ? "ROLE_SELLER" : "ROLE_ADMIN";
	}
}
