package com.zunza.pick;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zunza.pick.member.entity.MemberType;
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
		List<Member> members = new ArrayList<>();

		Member customer = Member.builder()
			.email("customer@email.com")
			.password(passwordEncoder.encode("password"))
			.name("김고객")
			.nickname("best customer")
			.address("서울 강남")
			.phone("010-1234-5678")
			.memberType(MemberType.CUSTOMER)
			.build();

		Member seller = Member.builder()
			.email("seller@email.com")
			.password(passwordEncoder.encode("password"))
			.name("김판매")
			.nickname("best seller")
			.address("서울 강남")
			.phone("010-1234-5671")
			.memberType(MemberType.SELLER)
			.build();

		Member admin = Member.builder()
			.email("admin@email.com")
			.password(passwordEncoder.encode("password"))
			.name("김관리")
			.nickname("best admin")
			.address("서울 강남")
			.phone("010-1232-5671")
			.memberType(MemberType.ADMIN)
			.build();

		members.add(customer);
		members.add(seller);
		members.add(admin);

		memberRepository.saveAll(members);
	}
}
