package com.tds.carpark.dto;

import java.time.OffsetDateTime;

public class ParkVehicleResponse {

    private final String vehicleReg;
    private final OffsetDateTime timeIn;

    public ParkVehicleResponse(String vehicleReg, OffsetDateTime timeIn) {
        this.vehicleReg = vehicleReg;
        this.timeIn = timeIn;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public OffsetDateTime getTimeIn() {
        return timeIn;
    }
}
