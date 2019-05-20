package com.flight.exercise.flight.excercise.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CouponRequestData {

    private Integer couponId;
    private BigDecimal price;
}
