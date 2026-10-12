package com.tds.carpark.service;

import com.tds.carpark.dto.SpaceAvailabilityResponse;
import com.tds.carpark.service.impl.ParkingServiceImpl;
import com.tds.carpark.util.FeeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.*;

public class ParkingServiceTests {

    private final Clock clock = Clock.fixed(Instant.parse("2026-10-12T11:00:00Z"), ZoneOffset.UTC);
    private ParkingService service;

    @BeforeEach
    void setUp(){
        service = new ParkingServiceImpl(5, clock, new FeeCalculator());
    }

    @Test
    void startEmptyTest(){
        var availability = service.availability();
        assertThat(availability.getAvailableSpaces()).isEqualTo(5);
        assertThat(availability.getOccupiedSpaces()).isEqualTo(0);
    }

    @Test
    void ParkVehicleAndUpdateOccupiedTest(){
        var response = service.park("ABC123", 1);
        assertThat(response.getVehicleReg()).isEqualTo("ABC123");
        assertThat(response.getTimeIn()).isEqualTo(clock.instant().atOffset(ZoneOffset.UTC));

        var availability = service.availability();
        assertThat(availability.getAvailableSpaces()).isEqualTo(4);
        assertThat(availability.getOccupiedSpaces()).isEqualTo(1);
    }

    @Test
    void carParkFullExceptionTest(){
        service.park("CAR1", 1);
        service.park("CAR2", 1);
        service.park("CAR3", 1);
        service.park("CAR4", 1);
        service.park("CAR5", 1);
        assertThatThrownBy(() -> service.park("CAR6", 1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void AlreadyParkedExceptionTest(){
        service.park("DUPLICATE", 1);
        assertThatThrownBy(() -> service.park("DUPLICATE", 1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void billingUnknownCarExceptionTest(){
        assertThatThrownBy(() -> service.bill("UNKNOWN")).isInstanceOf(RuntimeException.class);
    }

    @Test
    void billChargeAndFreeSpaceTest(){
        FeeCalculator feeCalculator = mock(FeeCalculator.class);
        Clock clock = Clock.fixed(Instant.parse("2026-10-12T11:00:00Z"), ZoneOffset.UTC);
        var localService = new ParkingServiceImpl(5, clock, feeCalculator);
        when(feeCalculator.calculate(any(), any(), any())).thenReturn(new BigDecimal("3.40"));

        localService.park("BILL1", 3);
        var fee = localService.bill("BILL1");
        assertThat(fee.getVehicleCharge()).isEqualTo(3.40);

        SpaceAvailabilityResponse availability = localService.availability();
        assertThat(availability.getAvailableSpaces()).isEqualTo(5);
    }

}
