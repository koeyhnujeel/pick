package com.zunza.pick.product.entity;

import com.zunza.pick.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(nullable = false)
	private String imageUrl;

	@Builder
	private ProductImage(Product product, String imageUrl) {
		this.product = product;
		this.imageUrl = imageUrl;
	}

	public static ProductImage of(Product product, String imageUrl) {
		return ProductImage.builder()
			.product(product)
			.imageUrl(imageUrl)
			.build();
	}
}
