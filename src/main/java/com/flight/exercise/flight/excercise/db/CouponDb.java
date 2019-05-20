package com.flight.exercise.flight.excercise.db;

import com.flight.exercise.flight.excercise.domain.CouponRequestData;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CouponDb {

    private final Map<Integer, BigDecimal> COUPON_DB;

    //wanted to use GUAVA cache but no libs available
    private Table<CouponRequestData, BigDecimal, Timestamp> COUPON_CACHE = HashBasedTable.create();


    public CouponDb() {
        this.COUPON_DB = initHash();
    }

    private Map<Integer, BigDecimal> initHash() {
        Map<Integer, BigDecimal> hash = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            hash.put(i, new BigDecimal((i % 5 * 10) + 10));
        }
        return hash;
    }

    public BigDecimal getDiscount(Integer couponId, BigDecimal price) {
        if (COUPON_CACHE.row(CouponRequestData.builder()
                .couponId(couponId)
                .price(price)
                .build()).size() != 0)
            return Lists.newArrayList(COUPON_CACHE.row(CouponRequestData.builder()
                    .couponId(couponId)
                    .price(price)
                    .build()).keySet()).get(0);

        BigDecimal newPrice = price;
        if (COUPON_DB.containsKey(couponId)) {
            newPrice = price.subtract(price.multiply(COUPON_DB.get(couponId)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        }
        COUPON_CACHE.put(CouponRequestData.builder()
                .price(price)
                .couponId(couponId)
                .build(), newPrice, Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
        return newPrice;
    }

    @Scheduled(fixedRate = 60000)
    public void removedCachedVals() {
        for (CouponRequestData row: COUPON_CACHE.rowKeySet()){
            if (Timestamp.valueOf(LocalDateTime.now()).after(Lists.newArrayList(COUPON_CACHE.row(row).values()).get(0)))
                COUPON_CACHE.remove(row, Lists.newArrayList(COUPON_CACHE.row(row).keySet()).get(0));
        }
    }

}
