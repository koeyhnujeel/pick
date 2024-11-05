package com.zunza.pick.member.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.zunza.pick.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRedisRepository {

	private final RedisTemplate<String, String> redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;

	public void saveRefreshToken(String email, String refreshToken) {
		redisTemplate.opsForValue()
			.set("RT:" + email,
				refreshToken,
				jwtTokenProvider.getRefreshTokenValidity(),
				TimeUnit.MILLISECONDS);
	}
}
