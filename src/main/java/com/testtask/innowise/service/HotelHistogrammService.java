package com.testtask.innowise.service;

import com.testtask.innowise.dto.response.HistogrammResponseDto;
import com.testtask.innowise.model.Hotel;
import com.testtask.innowise.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelHistogrammService {

    private final HotelRepository hotelRepository;

    public HotelHistogrammService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HistogrammResponseDto getHistogram(String param) {
        List<Hotel> hotels = hotelRepository.findAll();

        Map<String, Long> histogram;

        switch (param) {
            case "city":
                histogram = countHotelsByCity(hotels);
                break;
            case "brand":
                histogram = countHotelsByBrand(hotels);
                break;
            case "county":
                histogram = countHotelsByCounty(hotels);
                break;
            case "amenity":
                histogram = countHotelsByAmenity(hotels);
                break;
            default:
                throw new IllegalArgumentException("Invalid parameter: " + param);
        }

        Map<String, Integer> result = histogram.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().intValue()
                ));

        return new HistogrammResponseDto(result);
    }


    private Map<String, Long> countHotelsByCity(List<Hotel> hotels) {
        return hotels.stream()
                .collect(Collectors.groupingBy(
                        hotel -> hotel.getAddress().getCity(),
                        Collectors.counting()
                ));
    }


    private Map<String, Long> countHotelsByBrand(List<Hotel> hotels) {
        return hotels.stream()
                .collect(Collectors.groupingBy(
                        Hotel::getBrand,
                        Collectors.counting()
                ));
    }


    private Map<String, Long> countHotelsByCounty(List<Hotel> hotels) {
        return hotels.stream()
                .collect(Collectors.groupingBy(
                        hotel -> hotel.getAddress().getCountry(),
                        Collectors.counting()
                ));
    }

    private Map<String, Long> countHotelsByAmenity(List<Hotel> hotels) {
        return hotels.stream()
                .flatMap(hotel -> hotel.getAmenities().stream())
                .collect(Collectors.groupingBy(
                        amenity -> amenity.getName(),
                        Collectors.counting()
                ));
    }

}
