package com.zunza.pick.coupon.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zunza.pick.commons.advice.exception.CouponOutOfStockException;
import com.zunza.pick.commons.advice.exception.DuplicateCouponException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponUtil {

	private final RedisTemplate<String, String> redisTemplate;

	public Long incrementIssuedCoupons(Long couponId) {
		return redisTemplate
			.opsForValue()
			.increment("COUPON:" + couponId + ":COUNT");
	}

	public void validateIssuedLimit(Long currentIssuedCouponCount, Integer maxIssuanceCount) {
		if (currentIssuedCouponCount > maxIssuanceCount) {
			throw new CouponOutOfStockException();
		}
	}

	public void checkIssuedCoupon(Long couponId, Long memberId) {
		Long isIssued = redisTemplate.opsForSet().add("COUPON:" + couponId + ":ISSUED:MEMBERS", String.valueOf(memberId));
		if (isIssued == 0) {
			throw new DuplicateCouponException();
		}
	}
}
