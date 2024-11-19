package com.zunza.pick.coupon.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zunza.pick.coupon.controller.dto.CouponIssueDto;
import com.zunza.pick.coupon.service.CouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events/{eventId}")
@RequiredArgsConstructor
public class CouponController {

	private final CouponService couponService;

	@PostMapping("/coupons/{couponId}/issue")
	public ResponseEntity<Void> issue(
		@AuthenticationPrincipal Long memberId,
		@PathVariable Long eventId,
		@PathVariable Long couponId
	) {
		LocalDateTime requestedTime = LocalDateTime.now();
		CouponIssueDto couponIssueDto = CouponIssueDto.of(memberId, eventId, couponId, requestedTime);
		couponService.issue(couponIssueDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
