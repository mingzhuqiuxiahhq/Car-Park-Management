package com.tds.carpark.model;

import java.math.BigDecimal;

public enum VehicleType {
    SMALL(1, "0.10"),
    MEDIUM(2, "0.20"),
    LARGE(3, "0.40");

    private final int code;
    private final BigDecimal ratePerMinute;

    VehicleType(int code, String ratePerMinute) {
        this.code = code;
        this.ratePerMinute = new BigDecimal(ratePerMinute);
    }

    public int code() {
        return code;
    }

    public BigDecimal getRatePerMinute() {
        return ratePerMinute;
    }

    public static VehicleType fromCode(int code) {
        for (VehicleType type : values()) {
            if (type.code() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle type code: " + code);
    }

}
