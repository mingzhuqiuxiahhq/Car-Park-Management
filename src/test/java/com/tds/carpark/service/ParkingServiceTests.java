package com.tds.carpark.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import com.tds.carpark.service.impl.ParkingServiceImpl;
import com.tds.carpark.util.FeeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

public class ParkingServiceTests {

    private final Clock clock = Clock.fixed(Instant.parse("2026-10-12T11:00:00Z"), ZoneOffset.UTC);
    private ParkingService service;

    @BeforeEach
    void setUp(){
        service = new ParkingServiceImpl(100, clock, new FeeCalculator());
    }

    @Test
    void startEmpty(){
        var availability = service.availability();
        assertThat(availability).isNull();
    }

}
