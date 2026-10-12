package com.tds.carpark.util;

import com.tds.carpark.model.VehicleType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;

@Component
public class FeeCalculator {
    private static final BigDecimal SEC_PER_MIN = BigDecimal.valueOf(60);
    private static final BigDecimal BLOCK_MINS = BigDecimal.valueOf(5);
    private static final BigDecimal CHARGE_PER_BLOCK = BigDecimal.valueOf(1);
    private static final int MINUTE_DP = 3;

    public BigDecimal calculate(VehicleType type, OffsetDateTime timeIn, OffsetDateTime timeOut){
        long parkedSeconds = Math.max(0, Duration.between(timeIn, timeOut).getSeconds());
        BigDecimal minutes = BigDecimal.valueOf(parkedSeconds).divide(SEC_PER_MIN, MINUTE_DP, BigDecimal.ROUND_HALF_UP);

        BigDecimal perMinute = minutes.multiply(type.getRatePerMinute());
        BigDecimal blocks = minutes.divideToIntegralValue(BLOCK_MINS);
        BigDecimal blockCharge = blocks.multiply(CHARGE_PER_BLOCK);

        return perMinute.add(blockCharge).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
