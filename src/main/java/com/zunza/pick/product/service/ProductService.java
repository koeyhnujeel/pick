package com.zunza.pick.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zunza.pick.product.controller.dto.AddProductDto;
import com.zunza.pick.product.controller.response.AddProductResponse;
import com.zunza.pick.product.entity.Product;
import com.zunza.pick.product.entity.ProductImage;
import com.zunza.pick.product.repository.ProductImageRepository;
import com.zunza.pick.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductImageRepository productImageRepository;

	@Transactional
	public AddProductResponse addProduct(AddProductDto addProductDto) {
		Product product = Product.from(addProductDto);
		Product savedProduct = productRepository.save(product);

		List<String> savedProductImageUrls = new ArrayList<>();
		for (String imageUrl : addProductDto.getImageUrls()) {
			ProductImage productImage = ProductImage.of(savedProduct, imageUrl);
			ProductImage savedProductImage = productImageRepository.save(productImage);
			savedProductImageUrls.add(savedProductImage.getImageUrl());
		}

		return AddProductResponse.of(savedProduct, savedProductImageUrls);
	}
}
