package com.zunza.pick.coupon.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.zunza.pick.coupon.entity.MemberCoupon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class RedisQueueMemberCouponCreationStrategy implements MemberCouponCreationStrategy {

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public void create(MemberCoupon memberCoupon) {
		log.info("REDIS QUEUE 방식");
		redisTemplate.opsForList().rightPush("COUPON:ISSUE:QUEUE", memberCoupon);
	}
}
