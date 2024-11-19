package com.zunza.pick.coupon.entity;

import java.time.LocalDateTime;

import com.zunza.pick.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

	@Column(nullable = false)
	private Long eventId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;

	@Column(nullable = false)
	private Integer discountValue;

	@Column(nullable = false)
	private Integer minimumProductPrice;

	@Column(nullable = false)
	private LocalDateTime startDt;

	@Column(nullable = false)
	private LocalDateTime endDt;

	@Column(nullable = false)
	private Integer maxIssuanceCount;

	public Coupon(Long eventId, String name, DiscountType discountType, Integer discountValue,
		Integer minimumProductPrice,
		LocalDateTime startDt, LocalDateTime endDt, Integer maxIssuanceCount) {
		this.eventId = eventId;
		this.name = name;
		this.discountType = discountType;
		this.discountValue = discountValue;
		this.minimumProductPrice = minimumProductPrice;
		this.startDt = startDt;
		this.endDt = endDt;
		this.maxIssuanceCount = maxIssuanceCount;
	}
}
