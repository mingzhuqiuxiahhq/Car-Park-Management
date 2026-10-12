package com.tds.carpark.controller;

import com.tds.carpark.dto.*;
import com.tds.carpark.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public SpaceAvailabilityResponse spaces(){
        return parkingService.availability();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkVehicleResponse park(@Valid @RequestBody ParkVehicleRequest request) {
        return parkingService.park(request.getVehicleReg(), request.getVehicleType());
    }

    @PostMapping("/bill")
    public BillResponse bill(@Valid @RequestBody BillRequest request) {
        return parkingService.bill(request.getVehicleReg());
    }

}
