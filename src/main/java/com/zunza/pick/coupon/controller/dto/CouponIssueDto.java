package com.zunza.pick.coupon.controller.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CouponIssueDto {
	private Long memberId;
	private Long eventId;
	private Long couponId;
	private LocalDateTime requestedTime;

	@Builder
	private CouponIssueDto(Long memberId, Long eventId, Long couponId, LocalDateTime requestedTime) {
		this.memberId = memberId;
		this.eventId = eventId;
		this.couponId = couponId;
		this.requestedTime = requestedTime;
	}

	public static CouponIssueDto of(Long memberId, Long eventId, Long couponId, LocalDateTime requestedTime) {
		return CouponIssueDto.builder()
			.memberId(memberId)
			.eventId(eventId)
			.couponId(couponId)
			.requestedTime(requestedTime)
			.build();
	}
}
