package com.zunza.pick.member.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.zunza.pick.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenRedisRepository {

	private final RedisTemplate<String, String> redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;

	private static final String KEY_PREFIX = "RT:";

	public void saveRefreshToken(String memberId, String refreshToken) {
		redisTemplate.opsForValue()
			.set(KEY_PREFIX + memberId,
				refreshToken,
				jwtTokenProvider.getRefreshTokenValidity(),
				TimeUnit.MILLISECONDS);
	}

	public String findRefreshTokenById(String memberId) {
		return redisTemplate.opsForValue().get(KEY_PREFIX + memberId);
	}
}
