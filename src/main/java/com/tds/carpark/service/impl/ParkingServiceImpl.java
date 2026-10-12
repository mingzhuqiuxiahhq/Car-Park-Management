package com.tds.carpark.service.impl;

import com.tds.carpark.config.CarParkProperties;
import com.tds.carpark.dto.BillResponse;
import com.tds.carpark.dto.ParkVehicleResponse;
import com.tds.carpark.dto.SpaceAvailabilityResponse;
import com.tds.carpark.model.ParkedVehicle;
import com.tds.carpark.model.VehicleType;
import com.tds.carpark.util.FeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ParkingServiceImpl implements com.tds.carpark.service.ParkingService {
    private final int capacity;
    private final Clock clock;
    private final FeeCalculator feeCalculator;
    private final Queue<Integer> freeSpaces = new ConcurrentLinkedQueue<>();
    private final ConcurrentMap<String, ParkedVehicle> occupied = new ConcurrentHashMap<>();



    public ParkingServiceImpl(int capacity, Clock clock, FeeCalculator feeCalculator) {
        this.capacity = capacity;
        this.clock = clock;
        this.feeCalculator = feeCalculator;
        for (int space = 1; space <= capacity; space++) {
            freeSpaces.offer(space);
        }
    }

    @Autowired
    public ParkingServiceImpl(CarParkProperties capacity, Clock clock, FeeCalculator feeCalculator) {
        this(capacity.getCapacity(), clock, feeCalculator);
    }

    @Override
    public SpaceAvailabilityResponse availability() {
        int occupiedSpace = occupied.size();
        return new SpaceAvailabilityResponse(capacity - occupiedSpace, occupiedSpace);
    }

    @Override
    public ParkVehicleResponse park(String vehicleReg, Integer vehicleType) {
        VehicleType type = VehicleType.fromCode(vehicleType);
        String vrn = normalise(vehicleReg);


        AtomicBoolean created = new AtomicBoolean(false);
        ParkedVehicle parked = occupied.computeIfAbsent(vrn, key -> {
            Integer spaceNumber = freeSpaces.poll();
            if (spaceNumber == null) {
                return null;
            }
            created.set(true);
            return new ParkedVehicle(key, type, spaceNumber, OffsetDateTime.now(clock));
        });

        if (parked == null){
            throw new RuntimeException("Car park is full.");
        }
        if (!created.get()){
           throw new RuntimeException("Vehicle is already parked in the car park.");
        }

        return new ParkVehicleResponse(parked.getVehicleReg(), parked.getTimeIn());
    }

    @Override
    public BillResponse bill(String vehicleReg) {
        String vrn = normalise(vehicleReg);
        ParkedVehicle parked = occupied.remove(vrn);
        if (parked == null){
            throw new RuntimeException("Vehicle not found in car park.");
        }
        OffsetDateTime timeIn = parked.getTimeIn();
        OffsetDateTime timeOut = OffsetDateTime.now(clock);
        BigDecimal fee = feeCalculator.calculate(parked.getVehicleType(), timeIn, timeOut);

        return new BillResponse(vehicleReg, fee.doubleValue(), timeIn, timeOut);
    }

    private static String normalise(String vehicleReg){
        return vehicleReg.strip().toUpperCase();
    }
}
