package com.zunza.pick.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zunza.pick.exception.DuplicateNicknameException;
import com.zunza.pick.exception.DuplicatePhoneException;
import com.zunza.pick.member.dto.SignupDto;
import com.zunza.pick.member.dto.VerifyEmailDto;
import com.zunza.pick.member.dto.VerifyNicknameDto;
import com.zunza.pick.member.dto.VerifyPhoneDto;
import com.zunza.pick.member.entity.Member;
import com.zunza.pick.member.repository.MemberRepository;
import com.zunza.pick.exception.DuplicateEmailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

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
}
