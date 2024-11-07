package com.zunza.pick.product.entity;

import com.zunza.pick.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
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
	private String mainImage;

	@Column(nullable = false)
	private String mainThumbnail;

	@Column(nullable = false)
	private String detailImage;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
}
