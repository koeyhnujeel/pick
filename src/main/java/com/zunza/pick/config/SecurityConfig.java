package com.zunza.pick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zunza.pick.security.jwt.JwtAccessDeniedHandler;
import com.zunza.pick.security.jwt.JwtAuthenticationEntryPoint;
import com.zunza.pick.security.jwt.JwtAuthenticationFilter;
import com.zunza.pick.security.jwt.JwtLoginFilter;
import com.zunza.pick.security.jwt.JwtTokenProvider;
import com.zunza.pick.member.repository.TokenRedisRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;
	private final TokenRedisRepository tokenRedisRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final ObjectMapper objectMapper;

	@Bean
	public JwtLoginFilter jwtLoginFilter() throws Exception {
		JwtLoginFilter filter = new JwtLoginFilter(
			getAuthenticationManager(authenticationConfiguration),
			tokenRedisRepository,
			jwtTokenProvider,
			objectMapper
		);
		filter.setAuthenticationManager(getAuthenticationManager(authenticationConfiguration));
		filter.setFilterProcessesUrl("/api/auth/login");
		return filter;
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(jwtTokenProvider, objectMapper);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/api/auth/token/**").permitAll()
				.requestMatchers("/test1").permitAll()
			)

			.addFilterBefore(jwtLoginFilter(),
				UsernamePasswordAuthenticationFilter.class)
			.addFilterAfter(jwtAuthenticationFilter(),
				UsernamePasswordAuthenticationFilter.class)

			.exceptionHandling(exceptionHandling -> exceptionHandling
				.authenticationEntryPoint(new JwtAuthenticationEntryPoint(objectMapper))
				.accessDeniedHandler(new JwtAccessDeniedHandler(objectMapper))
			);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
