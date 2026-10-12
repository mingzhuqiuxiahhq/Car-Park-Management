package com.tds.carpark.model;

import java.time.OffsetDateTime;

public class ParkedVehicle {
    private final String vehicleReg;
    private final VehicleType vehicleType;
    private final int spaceNumber;
    private final OffsetDateTime timeIn;

    public ParkedVehicle(String vehicleReg, VehicleType vehicleType, int spaceNumber, OffsetDateTime timeIn) {
        this.vehicleReg = vehicleReg;
        this.vehicleType = vehicleType;
        this.spaceNumber = spaceNumber;
        this.timeIn = timeIn;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public OffsetDateTime getTimeIn() {
        return timeIn;
    }
}
