package com.testtask.innowise.service;

import com.testtask.innowise.dto.response.HotelResponseDto;
import com.testtask.innowise.mapper.HotelMapper;
import com.testtask.innowise.model.Hotel;
import com.testtask.innowise.repository.HotelRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelSearchService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelSearchService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Transactional(readOnly = true)
    public List<HotelResponseDto> searchHotels(String name, String brand, String city, String county, String amenities) {
        List<Hotel> hotels;

        if (name != null) {
            hotels = hotelRepository.findByNameContainingIgnoreCase(name);
        } else if (brand != null) {
            hotels = hotelRepository.findByBrandContainingIgnoreCase(brand);
        } else if (city != null) {
            hotels = hotelRepository.findByAddressCityContainingIgnoreCase(city);
        } else if (county != null) {
            hotels = hotelRepository.findByAddressCountryContainingIgnoreCase(county);
        } else if (amenities != null) {
            hotels = hotelRepository.findAll().stream()
                    .filter(hotel -> hotel.getAmenities().stream()
                            .anyMatch(amenity -> amenity.getName().equalsIgnoreCase(amenities)))
                    .collect(Collectors.toList());
        } else {
            hotels = hotelRepository.findAll();
        }

        return hotels.stream()
                .map(hotelMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}