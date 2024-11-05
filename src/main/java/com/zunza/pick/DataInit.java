package com.zunza.pick;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zunza.pick.member.constant.MemberType;
import com.zunza.pick.member.entity.Member;
import com.zunza.pick.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		Member member = Member.builder()
			.email("test@email.com")
			.password(passwordEncoder.encode("password"))
			.name("홍길동")
			.nickname("dong")
			.address("조선 한양")
			.phone("010-1234-5678")
			.memberType(MemberType.CUSTOMER)
			.build();

		memberRepository.save(member);
	}
}
