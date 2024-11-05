package com.zunza.pick.security.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zunza.pick.commons.ApiResponse;
import com.zunza.pick.commons.ErrorResponse;
import com.zunza.pick.member.repository.TokenRedisRepository;
import com.zunza.pick.member.request.LoginRequest;
import com.zunza.pick.member.response.TokenResponse;
import com.zunza.pick.security.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final TokenRedisRepository tokenRedisRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final ObjectMapper objectMapper;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {

		LoginRequest loginRequest = getLoginRequest(request);
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		return authenticationManager.authenticate(token);
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		try {
			return objectMapper.readValue(request.getInputStream(), LoginRequest.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException {

		CustomUserDetails userDetails = (CustomUserDetails)authResult.getPrincipal();
		String memberId = userDetails.getUserId().toString();
		List<String> roles = userDetails.authoritiesToStringList();

		String accessToken = jwtTokenProvider.createAccessToken(memberId, roles);
		String refreshToken = jwtTokenProvider.createRefreshToken(memberId);

		tokenRedisRepository.saveRefreshToken(memberId, refreshToken);

		TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
		ApiResponse<TokenResponse> apiResponse = ApiResponse.<TokenResponse>builder()
			.data(tokenResponse)
			.code(HttpServletResponse.SC_OK)
			.build();

		setResponse(response, HttpServletResponse.SC_OK);
		objectMapper.writeValue(response.getWriter(), apiResponse);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException failed) throws IOException {

		ErrorResponse errorResponse = ErrorResponse.builder()
			.message("이메일 또는 비밀번호를 확인해 주세요.")
			.code(HttpServletResponse.SC_UNAUTHORIZED)
			.build();

		setResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
		objectMapper.writeValue(response.getWriter(), errorResponse);
	}

	private static void setResponse(HttpServletResponse response, int StatusCode) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(StatusCode);
	}
}
