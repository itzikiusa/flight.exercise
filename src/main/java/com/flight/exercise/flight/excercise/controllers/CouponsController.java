package com.flight.exercise.flight.excercise.controllers;

import com.flight.exercise.flight.excercise.responses.CouponValidatorResponse;
import com.flight.exercise.flight.excercise.responses.HttpExceptionResponse;
import com.flight.exercise.flight.excercise.services.CalculateCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/flight/coupons")
@Slf4j
public class CouponsController {

    @Autowired
    private CalculateCouponService calculateCouponService;

    public CouponsController(CalculateCouponService calculateCouponService) {
        this.calculateCouponService = calculateCouponService;
    }

    @GetMapping(path = "/{coupon_id}/{price}", produces = "application/json")
    public ResponseEntity checkTicketAvailable(@PathVariable("coupon_id") Integer couponId, @PathVariable("price") BigDecimal price) {
        try {
            log.info(":checkTicketAvailable: applying coupon - " + couponId + "on price - " + price);
            CouponValidatorResponse response = calculateCouponService.calculateCoupon(couponId, price);
            log.info(":checkTicketAvailable: calculated value is - " + response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.info(":checkTicketAvailable: unable tp apply coupon - " + couponId + "on price - " + price, e);
            return ResponseEntity.status(500).body(HttpExceptionResponse.builder().errMsg(e.getMessage()).build());
        }
    }
}
