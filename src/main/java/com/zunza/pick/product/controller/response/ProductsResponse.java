package com.zunza.pick.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductsResponse {
	private Long id;
	private String name;
	private int price;
	private String imageUrl;
}
