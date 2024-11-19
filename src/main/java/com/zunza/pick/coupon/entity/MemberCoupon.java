package com.zunza.pick.coupon.entity;

import java.time.LocalDateTime;

import com.zunza.pick.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCoupon extends BaseEntity {

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long couponId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponStatus couponStatus;

	@Column
	private LocalDateTime usedDt;

	@Builder
	private MemberCoupon(Long memberId, Long couponId) {
		this.memberId = memberId;
		this.couponId = couponId;
		this.couponStatus = CouponStatus.UNUSED;
		this.usedDt = null;
	}

	public static MemberCoupon of(Long memberId, Long couponId) {
		return MemberCoupon.builder()
			.memberId(memberId)
			.couponId(couponId)
			.build();
	}
}
