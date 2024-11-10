package com.zunza.pick.product.controller.dto;

import lombok.Getter;

@Getter
public class ProductCursor {
	private Long lastId;
	private Integer lastPrice;

	public ProductCursor(Long lastId, Integer lastPrice) {
		this.lastId = lastId == null ? 0 : lastId;
		this.lastPrice = lastPrice == null ? 0 : lastPrice;
	}
}
