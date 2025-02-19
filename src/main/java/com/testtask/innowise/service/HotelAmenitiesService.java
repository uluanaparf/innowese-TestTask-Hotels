package com.testtask.innowise.service;

import com.testtask.innowise.model.Amenity;
import com.testtask.innowise.model.Hotel;
import com.testtask.innowise.repository.AmenityRepository;
import com.testtask.innowise.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelAmenitiesService {

    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;

    public HotelAmenitiesService(HotelRepository hotelRepository, AmenityRepository amenityRepository) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
    }

    @Transactional
    public void addAmenities(int hotelId, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));

        // Преобразуем уже существующие удобства в Set<String>
        Set<String> existingAmenities = hotel.getAmenities().stream()
                .map(Amenity::getName)
                .collect(Collectors.toSet());

        // Добавляем только новые удобства
        Set<Amenity> amenitiesToAdd = amenities.stream()
                .filter(name -> !existingAmenities.contains(name)) // Оставляем только новые
                .map(name -> amenityRepository.findByName(name).orElseGet(() -> new Amenity(name)))
                .collect(Collectors.toSet());

        hotel.getAmenities().addAll(amenitiesToAdd);
        hotelRepository.save(hotel);
    }
}
