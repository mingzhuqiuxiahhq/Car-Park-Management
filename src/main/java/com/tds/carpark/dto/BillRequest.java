package com.tds.carpark.dto;

import jakarta.validation.constraints.NotBlank;

public class BillRequest {

    @NotBlank(message = "Vehicle Reg must not be blank")
    private String vehicleReg;

    public BillRequest() {
    }

    public BillRequest(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }
    public String getVehicleReg() {
        return vehicleReg;
    }
}
