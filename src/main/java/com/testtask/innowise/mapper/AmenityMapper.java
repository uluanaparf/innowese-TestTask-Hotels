package com.testtask.innowise.mapper;

import com.testtask.innowise.model.Amenity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AmenityMapper {

    default String toDto(Amenity amenity) {
        return amenity.getName();
    }

    default Amenity toEntity(String amenityName) {
        Amenity amenity = new Amenity();
        amenity.setName(amenityName);
        return amenity;
    }
}
