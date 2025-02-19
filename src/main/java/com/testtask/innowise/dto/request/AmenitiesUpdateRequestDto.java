package com.testtask.innowise.dto.request;

import java.util.Set;

public class AmenitiesUpdateRequestDto {
    private Set<String> amenities;

    public Set<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<String> amenities) {
        this.amenities = amenities;
    }
}
