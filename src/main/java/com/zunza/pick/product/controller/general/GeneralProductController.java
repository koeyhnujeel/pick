package com.zunza.pick.product.controller.general;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.product.controller.dto.ProductCursor;
import com.zunza.pick.product.controller.request.SortCode;
import com.zunza.pick.product.controller.response.ProductsResponse;
import com.zunza.pick.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GeneralProductController {

	private final ProductService productService;

	@GetMapping("/api/general/products")
	public ResponseEntity<List<ProductsResponse>> getProducts(
		@RequestParam(required = false, defaultValue = SortCode.NEW) String sort,
		@RequestParam(required = false) Long lastId,
		@RequestParam(required = false) Integer lastPrice
	) {
		ProductCursor productCursor = new ProductCursor(lastId, lastPrice);
		List<ProductsResponse> products = productService.getProducts(productCursor, sort);
		return ResponseEntity.ok(products);
	}
}
