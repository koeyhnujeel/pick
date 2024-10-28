package com.zunza.pick.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zunza.pick.response.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

	@Autowired
	private HttpServletResponse httpServletResponse;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
		ServerHttpResponse response) {

		int statusCode = httpServletResponse.getStatus();

		if (body == null) {
			return ApiResponse.builder()
				.success(true)
				.data(null)
				.message(null)
				.code(statusCode)
				.build();
		}

		if (body instanceof ApiResponse<?>) {
			return body;
		}

		return ApiResponse.builder()
			.success(true)
			.data(body)
			.message(null)
			.code(statusCode)
			.build();
	}
}