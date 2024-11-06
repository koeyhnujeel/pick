package com.zunza.pick.member.dto;

import com.zunza.pick.member.request.RefreshTokenRequest;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RefreshTokenDto {
	private String refreshToken;

	@Builder
	private RefreshTokenDto(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public static RefreshTokenDto from(RefreshTokenRequest refreshTokenRequest) {
		return RefreshTokenDto.builder()
			.refreshToken(refreshTokenRequest.getRefreshToken())
			.build();
	}
}
