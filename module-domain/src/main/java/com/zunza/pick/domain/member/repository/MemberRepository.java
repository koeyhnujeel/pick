package com.zunza.pick.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zunza.pick.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email")
	Boolean isDuplicateEmail(@Param("email") String email);
}
