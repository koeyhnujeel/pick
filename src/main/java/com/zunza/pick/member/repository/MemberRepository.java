package com.zunza.pick.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zunza.pick.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email")
	Boolean isDuplicateEmail(@Param("email") String email);

	@Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.nickname = :nickname")
	Boolean isDuplicateNickname(@Param("nickname") String nickname);

	@Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.phone = :phone")
	Boolean isDuplicatePhone(@Param("phone") String phone);

	Optional<Member> findByEmail(String email);
}
