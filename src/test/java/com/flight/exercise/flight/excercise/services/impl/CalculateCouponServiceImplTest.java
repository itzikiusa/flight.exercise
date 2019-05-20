package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.CalculateCouponDao;
import com.flight.exercise.flight.excercise.dao.impl.CalculateCouponDaoImpl;
import com.flight.exercise.flight.excercise.db.CouponDb;
import com.flight.exercise.flight.excercise.responses.CouponValidatorResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculateCouponServiceImplTest {

    private CalculateCouponServiceImpl classUnderTest;

    @Before
    public void setUp() {
        CouponDb couponDb = new CouponDb();
        CalculateCouponDao calculateCouponDao = new CalculateCouponDaoImpl(couponDb);

        classUnderTest = new CalculateCouponServiceImpl(calculateCouponDao);
    }

    @Test
    public void getPriceDiscount() {
        CouponValidatorResponse response = classUnderTest.calculateCoupon(1,  new BigDecimal(100));
        assertEquals(response.getPrice().stripTrailingZeros().toString(), new BigDecimal(80).stripTrailingZeros().toString());
    }

    @Test
    public void getPriceDiscount_getFromCache() {
        classUnderTest.calculateCoupon(1,  new BigDecimal(100));
        CouponValidatorResponse response = classUnderTest.calculateCoupon(1,  new BigDecimal(100));
        assertEquals(response.getPrice().stripTrailingZeros().toString(), new BigDecimal(80).stripTrailingZeros().toString());
    }

    @Test
    public void getPriceDiscount_invalidCoupon() {
        CouponValidatorResponse response = classUnderTest.calculateCoupon(100000,  new BigDecimal(100));
        assertEquals(response.getPrice().stripTrailingZeros().toString(), new BigDecimal(100).stripTrailingZeros().toString());
        assertEquals(response.getErrMsg(), "invalid_coupon");
    }

}