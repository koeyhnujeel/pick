package com.zunza.pick.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private boolean success;

	@JsonIgnore
	private T data;

	@JsonIgnore
	private String message;

	private int code;

	@Builder
	public ApiResponse(boolean success, T data, String message, int code) {
		this.success = success;
		this.data = data;
		this.message = message;
		this.code = code;
	}
}
