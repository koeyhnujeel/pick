package com.zunza.pick.product.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
	IN_STOCK("재고보유"),
	SOLD_OUT("품절");

	private String value;

	ProductStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public ProductStatus from(String status) {
		for (ProductStatus value : ProductStatus.values()) {
			if (value.getValue().equals(status)) {
				return value;
			}
		}
		return null;
	}
}
