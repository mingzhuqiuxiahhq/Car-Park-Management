package com.tds.carpark.service;

import com.tds.carpark.dto.BillResponse;
import com.tds.carpark.dto.ParkVehicleResponse;
import com.tds.carpark.dto.SpaceAvailabilityResponse;

public interface ParkingService {
    SpaceAvailabilityResponse availability();
    ParkVehicleResponse park(String vehicleReg, Integer vehicleType);
    BillResponse bill(String vehicleReg);

}
