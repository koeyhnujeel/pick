package com.zunza.pick.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access-token-validity}")
	private long accessTokenValidity;

	@Value("${jwt.refresh-token-validity}")
	private long refreshTokenValidity;

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String createAccessToken(String memberId, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(memberId);
		claims.put("roles", roles);

		Date now = new Date();
		Date validity = new Date(now.getTime() + accessTokenValidity);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public String createRefreshToken() {
		Date now = new Date();
		Date validity = new Date(now.getTime() + refreshTokenValidity);

		return Jwts.builder()
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public long getRefreshTokenValidity() {
		return refreshTokenValidity;
	}
}
