package com.tds.carpark.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tds.carpark.model.VehicleType;
import org.junit.jupiter.api.Test;

public class VehicleTypeTests {
    @Test
    void mapCodeToType(){
        assertThat(VehicleType.fromCode(1)).isEqualTo(VehicleType.SMALL);
        assertThat(VehicleType.fromCode(2)).isEqualTo(VehicleType.MEDIUM);
        assertThat(VehicleType.fromCode(3)).isEqualTo(VehicleType.LARGE);
    }

    @Test
    void rejectWrongCode() {
        assertThatThrownBy(() -> VehicleType.fromCode(10)).isInstanceOf(IllegalArgumentException.class);
    }
}
