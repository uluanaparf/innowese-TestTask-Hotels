package com.testtask.innowise.mapper;

import com.testtask.innowise.dto.request.HotelCreateRequestDto;
import com.testtask.innowise.dto.response.HotelDetailsResponseDto;
import com.testtask.innowise.dto.response.HotelResponseDto;
import com.testtask.innowise.model.Address;
import com.testtask.innowise.model.Amenity;
import com.testtask.innowise.model.Contacts;
import com.testtask.innowise.model.Hotel;
import org.mapstruct.*;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { AddressMapper.class, ContactMapper.class, ArrivalTimeMapper.class, AmenityMapper.class })
public interface HotelMapper {

    @Mapping(source = "contacts", target = "phone", qualifiedByName = "contactToPhone")
    @Mapping(source = "address", target = "address", qualifiedByName = "formatAddress")
    HotelResponseDto toResponseDto(Hotel hotel);

    @Named("contactToPhone")
    default String contactToPhone(Contacts contacts) {
        return contacts != null ? contacts.getPhone() : null;
    }

    @Named("formatAddress")
    default String formatAddress(Address address) {
        if (address == null) {
            return null;
        }
        return address.getHouseNumber() + " " + address.getStreet() + ", " +
                address.getCity() + ", " + address.getCountry() + ", " +
                address.getPostCode();
    }
    HotelDetailsResponseDto toDetailsResponseDto(Hotel hotel);

    @Mapping(target = "amenities", ignore = true)
    Hotel toEntity(HotelCreateRequestDto hotelCreateRequestDto);

    List<HotelResponseDto> toShortDtoList(List<Hotel> hotels);

    @Named("mapAmenities")
    default Set<String> mapAmenities(Set<Amenity> amenities) {
        return amenities.stream().map(Amenity::getName).collect(Collectors.toSet());
    }


}

