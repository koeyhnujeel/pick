package com.zunza.pick.commons.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.zunza.pick.commons.advice.exception.MemberNotFoundException;
import com.zunza.pick.member.entity.Member;
import com.zunza.pick.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(MemberNotFoundException::new);

		return new CustomUserDetails(member);
	}
}
