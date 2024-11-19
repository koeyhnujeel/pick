package com.zunza.pick.coupon.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zunza.pick.commons.advice.exception.CouponNotFoundException;
import com.zunza.pick.coupon.entity.Coupon;
import com.zunza.pick.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponCacheService {

	private final CouponRepository couponRepository;

	@Cacheable(cacheNames = "couponCache", key = "#couponId")
	public Coupon getCoupon(Long couponId) {
		return couponRepository.findById(couponId)
			.orElseThrow(CouponNotFoundException::new);
	}
}
