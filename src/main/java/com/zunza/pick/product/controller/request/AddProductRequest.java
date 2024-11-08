package com.zunza.pick.product.controller.request;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.zunza.pick.product.entity.Category;
import com.zunza.pick.product.entity.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddProductRequest {

	@NotBlank(message = "상품 이름을 입력해 주세요.")
	@Length(min = 3, max = 30, message = "상품 이름은 3~30자 사이로 입력해 주세요.")
	private String name;

	@NotNull(message = "상품 가격을 입력해 주세요.")
	private int price;

	@NotBlank(message = "상품 설명을 입력해 주세요.")
	@Length(min = 3, max = 100, message = "상품 셜명은 3~100자 사이로 입력해 주세요.")
	private String description;

	@NotNull(message = "상품 재고 수량을 입력해 주세요.")
	private int stock;

	@NotNull(message = "상품 이미지를 등록해 주세요.")
	private List<String> imageUrls;

	@NotNull(message = "상품 카테고리를 설정해 주세요.")
	private Category category;

	@NotNull(message = "상품 판매여부를 설정해 주세요.")
	private ProductStatus status;
}
