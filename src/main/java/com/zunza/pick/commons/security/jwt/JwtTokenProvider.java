package com.zunza.pick.commons.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
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

	public String createRefreshToken(String memberId) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + refreshTokenValidity);

		return Jwts.builder()
			.setSubject(memberId)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(getKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	public long getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public boolean validateAccessToken(String accessToken) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(accessToken);
			return true;
		} catch (ExpiredJwtException e) {
			throw new ExpiredJwtException(null, null, "만료된 토큰입니다.");
		} catch (SignatureException e) {
			throw new SignatureException("변조된 토큰입니다.");
		}
	}

	public boolean validateRefreshToken(String refreshToken) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(refreshToken);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Authentication getAuthentication(String accessToken) {
		Claims claims = getClaims(accessToken);
		Long memberId = Long.parseLong(claims.getSubject());
		List<String> roles = claims.get("roles", List.class);

		List<GrantedAuthority> authorities = roles.stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(memberId, null, authorities);
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String getSubject(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}
}
