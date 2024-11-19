package com.zunza.pick.coupon.strategy;

import org.springframework.stereotype.Component;

import com.zunza.pick.coupon.entity.MemberCoupon;
import com.zunza.pick.coupon.repository.MemberCouponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultMemberCouponCreationStrategy implements MemberCouponCreationStrategy {

	private final MemberCouponRepository memberCouponRepository;

	@Override
	public void create(MemberCoupon memberCoupon) {
		log.info("기본 저장 방식");
		memberCouponRepository.save(memberCoupon);
	}
}
