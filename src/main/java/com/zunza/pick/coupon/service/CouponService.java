package com.zunza.pick.coupon.service;

import org.springframework.stereotype.Service;

import com.zunza.pick.coupon.controller.dto.CouponIssueDto;
import com.zunza.pick.coupon.entity.Coupon;
import com.zunza.pick.coupon.entity.MemberCoupon;
import com.zunza.pick.coupon.strategy.MemberCouponCreationStrategy;
import com.zunza.pick.coupon.util.CouponUtil;
import com.zunza.pick.event.entity.Event;
import com.zunza.pick.event.service.EventCacheService;
import com.zunza.pick.event.util.EventUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

	private final MemberCouponCreationStrategy memberCouponCreationStrategy;
	private final CouponCacheService couponCacheService;
	private final EventCacheService eventCacheService;
	private final CouponUtil couponUtil;
	private final EventUtil eventUtil;

	public void issue(CouponIssueDto couponIssueDto) {

		// 중복 발급 검증
		couponUtil.checkIssuedCoupon(couponIssueDto.getCouponId(), couponIssueDto.getMemberId());

		// 이벤트 시간 검증
		Event event = eventCacheService.getEvent(couponIssueDto.getEventId());
		eventUtil.validateEventTime(couponIssueDto.getRequestedTime(), event.getStartDt(), event.getEndDt());

		// 쿠폰 재고 검증
		Long currentIssuedCouponCount = couponUtil.incrementIssuedCoupons(couponIssueDto.getCouponId());
		Coupon coupon = couponCacheService.getCoupon(couponIssueDto.getCouponId());
		couponUtil.validateIssuedLimit(currentIssuedCouponCount, coupon.getMaxIssuanceCount());

		// 쿠폰 발급
		MemberCoupon memberCoupon = MemberCoupon.of(couponIssueDto.getMemberId(), couponIssueDto.getCouponId());
		memberCouponCreationStrategy.create(memberCoupon);
	}
}
