package com.zunza.pick.product.controller.response;

import java.time.Instant;
import java.util.List;

import com.zunza.pick.product.entity.Category;
import com.zunza.pick.product.entity.Product;
import com.zunza.pick.product.entity.ProductStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddProductResponse {
	private Long id;
	private String name;
	private int price;
	private String description;
	private int stock;
	private Category category;
	private ProductStatus status;
	private List<String> imageUrls;
	private Instant createdAt;
	private Instant modifiedDt;

	@Builder
	private AddProductResponse(Long id, String name, int price, String description, int stock, Category category,
		ProductStatus status, List<String> imageUrls, Instant createdAt, Instant modifiedDt) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.category = category;
		this.status = status;
		this.imageUrls = imageUrls;
		this.createdAt = createdAt;
		this.modifiedDt = modifiedDt;
	}

	public static AddProductResponse of(Product savedProduct, List<String> savedImageUrls) {
		return AddProductResponse.builder()
			.id(savedProduct.getId())
			.name(savedProduct.getName())
			.price(savedProduct.getPrice())
			.description(savedProduct.getDescription())
			.stock(savedProduct.getStock())
			.category(savedProduct.getCategory())
			.status(savedProduct.getStatus())
			.imageUrls(savedImageUrls)
			.createdAt(savedProduct.getCreatedDt())
			.modifiedDt(savedProduct.getModifiedDt())
			.build();
	}
}
