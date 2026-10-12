package com.tds.carpark.dto;

public class SpaceAvailabilityResponse {
    private final int availableSpaces;
    private final int occupiedSpaces;

    public SpaceAvailabilityResponse(int availableSpaces, int occupiedSpaces) {
        this.availableSpaces = availableSpaces;
        this.occupiedSpaces = occupiedSpaces;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }
}
