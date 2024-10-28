package com.zunza.pick.advice;

import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zunza.pick.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

		List<String> errorMessages = e.getFieldErrors().stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.toList();

		String message = errorMessages.size() == 1 ? errorMessages.get(0) : null;
		List<String> messages = errorMessages.size() > 1 ? errorMessages : null;
		int code = e.getStatusCode().value();

		ErrorResponse errorResponse = ErrorResponse.builder()
			.success(false)
			.message(message)
			.messages(messages)
			.code(code)
			.build();

		return ResponseEntity.status(code).body(errorResponse);
	}
}
