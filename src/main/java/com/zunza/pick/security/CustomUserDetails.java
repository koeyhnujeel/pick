package com.zunza.pick.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zunza.pick.member.constant.MemberType;
import com.zunza.pick.member.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final Member member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		MemberType memberType = member.getMemberType();
		String authority = memberType.equals(MemberType.CUSTOMER) ? "ROLE_USER" : "ROLE_ADMIN";
		return List.of(new SimpleGrantedAuthority(authority));
	}

	public List<String> authoritiesToStringList() {
		return this.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.toList();
	}

	@Override
	public String getUsername() {
		return member.getEmail();
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	public Long getUserId() {
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
