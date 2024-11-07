package com.zunza.pick.product.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

public enum Category {
	TOP("001", "상의"),
	PANTS("002", "하의"),
	SHOES("003", "신발");

	@Getter
	private String code;
	private String value;

	Category(String code, String value) {
		this.code = code;
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public Category form(String category) {
		for (Category value : Category.values()) {
			if (value.getValue().equals(category)) {
				return value;
			}
		}
		return null;
	}
}
