package com.zunza.pick.product.repository;

import java.util.List;

import com.zunza.pick.product.controller.dto.ProductCursor;
import com.zunza.pick.product.controller.response.ProductsResponse;

public interface ProductCustomRepository {
	List<ProductsResponse> findProducts(ProductCursor productCursor, String sort);
}
