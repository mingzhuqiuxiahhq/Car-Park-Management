package com.tds.carpark.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParkVehicleRequest {
    @NotBlank(message = "Vehicle Reg must not be blank")
    String vehicleReg;

    // Min and Max can be externalised
    @Min(1)
    @Max(3)
    @NotNull
    Integer vehicleType;

    public ParkVehicleRequest() {
    }

    public ParkVehicleRequest(String vehicleReg, Integer vehicleType) {
        this.vehicleReg = vehicleReg;
        this.vehicleType = vehicleType;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }
}
