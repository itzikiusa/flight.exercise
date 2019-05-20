package com.flight.exercise.flight.excercise.dao;

import java.math.BigDecimal;

public interface CalculateCouponDao {
    BigDecimal calculateCoupon(Integer couponId, BigDecimal price);
}
