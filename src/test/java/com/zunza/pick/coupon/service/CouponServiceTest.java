package com.zunza.pick.coupon.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zunza.pick.coupon.controller.dto.CouponIssueDto;
import com.zunza.pick.coupon.repository.CouponRepository;
import com.zunza.pick.coupon.repository.MemberCouponRepository;
import com.zunza.pick.event.repository.EventRepository;

@SpringBootTest
class CouponServiceTest {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponService couponService;

	@Autowired
	private MemberCouponRepository memberCouponRepository;

	@Test
	void 여려_명이_동시에_쿠폰_발급() throws InterruptedException {

		int threadCount = 1000000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for(int i = 1; i <= threadCount; i++) {
			CouponIssueDto couponIssueDto = CouponIssueDto.of((long)i, 1L, 1L, LocalDateTime.now());
			executorService.execute(() -> {
				try {
					couponService.issue(couponIssueDto);
				} finally {
					// count 값을 1 감소
					latch.countDown();
				}
			});
		}

		latch.await();

		// Thread.sleep(60000 * 2);

		long count = memberCouponRepository.count();
		assertEquals(10000, count);
	}
}
