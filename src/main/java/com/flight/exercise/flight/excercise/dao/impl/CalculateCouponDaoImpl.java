package com.flight.exercise.flight.excercise.dao.impl;

import com.flight.exercise.flight.excercise.dao.CalculateCouponDao;
import com.flight.exercise.flight.excercise.db.CouponDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateCouponDaoImpl implements CalculateCouponDao {

    @Autowired
    private final CouponDb couponDb;

    public CalculateCouponDaoImpl(CouponDb couponDb) {
        this.couponDb = couponDb;
    }

    @Override
    public BigDecimal calculateCoupon(Integer couponId, BigDecimal price) {
        return couponDb.getDiscount(couponId, price);
    }
}
