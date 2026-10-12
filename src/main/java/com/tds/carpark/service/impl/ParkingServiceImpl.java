package com.tds.carpark.service.impl;

import com.tds.carpark.config.CarParkProperties;
import com.tds.carpark.dto.BillResponse;
import com.tds.carpark.dto.ParkVehicleResponse;
import com.tds.carpark.dto.SpaceAvailabilityResponse;
import com.tds.carpark.util.FeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class ParkingServiceImpl implements com.tds.carpark.service.ParkingService {
    private final int capacity;
    private final Clock clock;
    private final FeeCalculator feeCalculator;

    public ParkingServiceImpl(int capacity, Clock clock, FeeCalculator feeCalculator) {
        this.capacity = capacity;
        this.clock = clock;
        this.feeCalculator = feeCalculator;
    }

    @Autowired
    public ParkingServiceImpl(CarParkProperties capacity, Clock clock, FeeCalculator feeCalculator) {
        this.capacity = capacity.getCapacity();
        this.clock = clock;
        this.feeCalculator = feeCalculator;
    }

    @Override
    public SpaceAvailabilityResponse availability() {
        return null;
    }

    @Override
    public ParkVehicleResponse park(String vehicleReg, Integer vehicleType) {
        return null;
    }

    @Override
    public BillResponse bill(String vehicleReg) {
        return null;
    }
}
