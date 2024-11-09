package com.zunza.pick.product.controller.seller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.product.controller.dto.AddProductDto;
import com.zunza.pick.product.controller.request.AddProductRequest;
import com.zunza.pick.product.controller.response.AddProductResponse;
import com.zunza.pick.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SellerProductController {

	private final ProductService productService;

	@PostMapping("/api/seller/products")
	public ResponseEntity<AddProductResponse> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {
		AddProductDto addProductDto = AddProductDto.from(addProductRequest);
		AddProductResponse addProductResponse = productService.addProduct(addProductDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(addProductResponse);
	}
}
