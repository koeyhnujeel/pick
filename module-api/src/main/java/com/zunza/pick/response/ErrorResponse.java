package com.zunza.pick.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

	private boolean success;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> messages;

	private int code;

	@Builder
	public ErrorResponse(boolean success, String message, List<String> messages, int code) {
		this.success = success;
		this.message = message;
		this.messages = messages;
		this.code = code;
	}
}
