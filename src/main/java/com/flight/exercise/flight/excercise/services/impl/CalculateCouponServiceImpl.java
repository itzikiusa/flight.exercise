package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.CalculateCouponDao;
import com.flight.exercise.flight.excercise.responses.CouponValidatorResponse;
import com.flight.exercise.flight.excercise.services.CalculateCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class CalculateCouponServiceImpl implements CalculateCouponService {

    @Autowired
    private final CalculateCouponDao calculateCouponDao;

    public CalculateCouponServiceImpl(CalculateCouponDao calculateCouponDao) {
        this.calculateCouponDao = calculateCouponDao;
    }

    @Override
    public CouponValidatorResponse calculateCoupon(Integer couponId, BigDecimal price) {
        log.debug(":calculateCoupon: calculating coupon from DB");
        BigDecimal calculatedPrice = calculateCouponDao.calculateCoupon(couponId, price);
        if (price.compareTo(calculatedPrice) == 0) {
            log.error(":calculateCoupon: coupon - " + couponId + " wasn't found in our system");
            return CouponValidatorResponse.builder()
                    .errMsg("invalid_coupon")
                    .price(price)
                    .build();
        }
        return CouponValidatorResponse.builder()
                .price(calculatedPrice)
                .build();
    }
}
