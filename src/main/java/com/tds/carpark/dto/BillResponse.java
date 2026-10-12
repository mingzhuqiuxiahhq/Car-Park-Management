package com.tds.carpark.dto;

import java.time.OffsetDateTime;

public class BillResponse {
    private final String vehicleReg;
    private final double vehicleCharge;
    private final OffsetDateTime timeIn;
    private final OffsetDateTime timeOut;

    public BillResponse(String vehicleReg, double vehicleCharge, OffsetDateTime timeIn, OffsetDateTime timeOut) {
        this.vehicleReg = vehicleReg;
        this.vehicleCharge = vehicleCharge;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public double getVehicleCharge() {
        return vehicleCharge;
    }

    public OffsetDateTime getTimeIn() {
        return timeIn;
    }

    public OffsetDateTime getTimeOut() {
        return timeOut;
    }
}
