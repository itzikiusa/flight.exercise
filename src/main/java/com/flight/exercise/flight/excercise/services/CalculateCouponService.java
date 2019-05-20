package com.flight.exercise.flight.excercise.services;

import com.flight.exercise.flight.excercise.responses.CouponValidatorResponse;

import java.math.BigDecimal;

public interface CalculateCouponService {
    CouponValidatorResponse calculateCoupon(Integer couponId, BigDecimal price);
}
