package com.zunza.pick.commons.cache;

import lombok.Getter;

@Getter
public enum CacheType {

	PRODUCTS_CACHE("productsCache", 10, 1000),
	EVENT_CACHE("eventCache", 10, 100),
	COUPON_CACHE("couponCache", 10, 100);

	private String cacheName;
	private long expireAfterWrite;
	private long maximumSize;

	CacheType(String cacheName, long expireAfterWrite, long maximumSize) {
		this.cacheName = cacheName;
		this.expireAfterWrite = expireAfterWrite;
		this.maximumSize = maximumSize;
	}
}
