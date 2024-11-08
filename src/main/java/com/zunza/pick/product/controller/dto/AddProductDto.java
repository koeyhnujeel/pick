package com.zunza.pick.product.controller.dto;

import java.util.List;

import com.zunza.pick.product.controller.request.AddProductRequest;
import com.zunza.pick.product.entity.Category;
import com.zunza.pick.product.entity.ProductStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddProductDto {
	private String name;
	private int price;
	private String description;
	private int stock;
	private List<String> imageUrls;
	private Category category;
	private ProductStatus status;

	@Builder
	private AddProductDto(String name, int price, String description, int stock, List<String> imageUrls,
		Category category,
		ProductStatus status) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.imageUrls = imageUrls;
		this.category = category;
		this.status = status;
	}

	public static AddProductDto from(AddProductRequest addProductRequest) {
		return AddProductDto.builder()
			.name(addProductRequest.getName())
			.price(addProductRequest.getPrice())
			.description(addProductRequest.getDescription())
			.stock(addProductRequest.getStock())
			.imageUrls(addProductRequest.getImageUrls())
			.category(addProductRequest.getCategory())
			.status(addProductRequest.getStatus())
			.build();
	}
}
