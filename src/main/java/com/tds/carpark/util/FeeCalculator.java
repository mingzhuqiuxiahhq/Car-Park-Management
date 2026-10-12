package com.tds.carpark.util;

import com.tds.carpark.model.VehicleType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Component
public class FeeCalculator {

    BigDecimal calculate(VehicleType type, OffsetDateTime timeIn, OffsetDateTime timeOut){
        return BigDecimal.ZERO;
    }

}
