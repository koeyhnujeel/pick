package com.zunza.pick.product.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zunza.pick.product.controller.dto.ProductCursor;
import com.zunza.pick.product.controller.request.SortCode;
import com.zunza.pick.product.controller.response.ProductsResponse;
import com.zunza.pick.product.entity.QProduct;
import com.zunza.pick.product.entity.QProductImage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private QProduct product = QProduct.product;
	private QProductImage productImage = QProductImage.productImage;

	@Override
	public List<ProductsResponse> findProducts(ProductCursor productCursor, String sort) {
		BooleanBuilder builder = new BooleanBuilder();

		return jpaQueryFactory
			.select(
				Projections.constructor(
					ProductsResponse.class,
					product.id,
					product.name,
					product.price,
					productImage.imageUrl
				)
			)
			.from(product)
			.innerJoin(productImage)
			.on(productImage.product.eq(product).and(productImage.imageUrl.contains("main-")))
			.where(getFilter(productCursor, sort))
			.orderBy(getOrderSpecifier(sort))
			.limit(12)
			.fetch();
	}

	private BooleanExpression getFilter(ProductCursor productCursor, String sort) {
		switch (sort) {
			case SortCode.NEW -> { return applyNewProductFilter(productCursor.getLastId()); }
			case SortCode.HIGH_PRICE -> { return applyHighPriceProductFilter(productCursor.getLastId(), productCursor.getLastPrice()); }
			case SortCode.LOW_PRICE -> { return applyLowPriceProductFilter(productCursor.getLastId(), productCursor.getLastPrice()); }
		}
		return null;
	}

	private BooleanExpression applyNewProductFilter(Long lastId) {
		if (lastId == 0) {
			return null;
		}
		return product.id.lt(lastId);
	}

	private BooleanExpression applyHighPriceProductFilter(Long lastId, Integer lastPrice) {
		if (lastPrice == 0) {
			return null;
		}
		return product.price.lt(lastPrice)
			.or(product.price.eq(lastPrice)
			.and(product.id.lt(lastId))
			);
	}

	private BooleanExpression applyLowPriceProductFilter(Long lastId, Integer lastPrice) {
		if (lastPrice == 0) {
			return null;
		}
		return product.price.gt(lastPrice)
			.or(product.price.eq(lastPrice)
			.and(product.id.lt(lastId))
			);
	}

	private OrderSpecifier<?>[] getOrderSpecifier(String sort) {
		OrderSpecifier<?> defaultSort = product.id.desc();

		OrderSpecifier<?> priceSort = switch (sort) {
			case SortCode.LOW_PRICE -> product.price.asc();
			case SortCode.HIGH_PRICE -> product.price.desc();
			default -> null;
		};

		return priceSort != null ? new OrderSpecifier[] {priceSort, defaultSort} : new OrderSpecifier[] {defaultSort};
	}
}
