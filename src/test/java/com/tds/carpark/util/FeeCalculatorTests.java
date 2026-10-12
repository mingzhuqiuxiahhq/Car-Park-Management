package com.tds.carpark.util;
import static org.assertj.core.api.Assertions.assertThat;
import com.tds.carpark.model.VehicleType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class FeeCalculatorTests {
    private final FeeCalculator feeCalculator = new FeeCalculator();

    private static OffsetDateTime time(){
        return OffsetDateTime.parse("2026-10-12T12:00:00Z");
    }

    @Test
    void chargeFeeUnderFiveMinutes() {
        OffsetDateTime timeOut = time().plusSeconds(245);
        BigDecimal charge = feeCalculator.calculate(VehicleType.MEDIUM, time(), timeOut);
        assertThat(charge).isEqualByComparingTo("0.82");
    }

    @Test
    void chargeFeeOverFiveMinutes() {
        OffsetDateTime timeOut = time().plusSeconds(305);
        BigDecimal charge = feeCalculator.calculate(VehicleType.MEDIUM, time(), timeOut);
        assertThat(charge).isEqualByComparingTo("2.02");
    }
}
