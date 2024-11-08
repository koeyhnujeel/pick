package com.zunza.pick.product.entity;

import com.zunza.pick.commons.BaseEntity;
import com.zunza.pick.product.controller.dto.AddProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int stock;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@Builder
	private Product(String name, int price, String description, int stock, Category category, ProductStatus status) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.category = category;
		this.status = status;
	}

	public static Product from(AddProductDto addProductDto) {
		return Product.builder()
			.name(addProductDto.getName())
			.price(addProductDto.getPrice())
			.description(addProductDto.getDescription())
			.stock(addProductDto.getStock())
			.category(addProductDto.getCategory())
			.status(addProductDto.getStatus())
			.build();
	}
}
