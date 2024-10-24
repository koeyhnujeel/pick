package com.zunza.pick.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberType {
	CUSTOMER("고객"),
	SELLER("판매자"),
	ADMIN("관리자");

	private String value;

	MemberType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public MemberType form(String memberType) {
		for (MemberType type : MemberType.values()) {
			if (type.getValue().equals(memberType)) {
				return type;
			}
		}
		return null;
	}
}
