package com.zunza.pick.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private boolean success;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	private int code;

	@Builder
	public ApiResponse(boolean success, T data, int code) {
		this.success = success;
		this.data = data;
		this.code = code;
	}
}
