package com.zunza.pick.coupon.scheduler;

import java.io.IOException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zunza.pick.coupon.entity.MemberCoupon;
import com.zunza.pick.coupon.repository.MemberCouponRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberCouponScheduler {

	private final MemberCouponRepository memberCouponRepository;
	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedDelay = 1000) // 1초마다 실행
	public void processCouponIssue() throws IOException {
		String value = redisTemplate.opsForList().leftPop("COUPON:ISSUE:QUEUE");
		if (value != null) {
			MemberCoupon memberCoupon = objectMapper.readValue(value, MemberCoupon.class);
			memberCouponRepository.save(memberCoupon);
		}
	}
}
