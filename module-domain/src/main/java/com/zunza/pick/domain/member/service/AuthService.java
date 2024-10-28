package com.zunza.pick.domain.member.service;

import org.springframework.stereotype.Service;

import com.zunza.pick.domain.member.dto.SignupDto;
import com.zunza.pick.domain.member.dto.VerifyEmailDto;
import com.zunza.pick.domain.member.entity.Member;
import com.zunza.pick.domain.member.repository.MemberRepository;
import com.zunza.pick.exception.DuplicateEmailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;

	public void signup(SignupDto signupDto) {
		Member member = Member.from(signupDto);
		memberRepository.save(member);
	}

	public void verifyDuplicateEmail(VerifyEmailDto verifyEmailDto) {
		Boolean duplicateEmail = memberRepository.isDuplicateEmail(verifyEmailDto.getEmail());
		if (duplicateEmail) {
			throw new DuplicateEmailException();
		}
	}
}
