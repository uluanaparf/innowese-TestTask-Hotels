package com.testtask.innowise.controller;

import com.testtask.innowise.dto.request.HotelCreateRequestDto;
import com.testtask.innowise.dto.response.HotelResponseDto;
import com.testtask.innowise.dto.response.HotelDetailsResponseDto;
import com.testtask.innowise.service.HotelAmenitiesService;
import com.testtask.innowise.service.HotelHistogrammService;
import com.testtask.innowise.service.HotelSearchService;
import com.testtask.innowise.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
public class HotelController {

    private final HotelService hotelService;
    private final HotelSearchService hotelSearchService;
    private final HotelAmenitiesService hotelAmenitiesService;
    private final HotelHistogrammService hotelHistogrammService;

    @Autowired
    public HotelController(HotelService hotelService, HotelSearchService hotelSearchService, HotelAmenitiesService hotelAmenitiesService, HotelHistogrammService hotelHistogrammService) {
        this.hotelService = hotelService;
        this.hotelSearchService = hotelSearchService;
        this.hotelAmenitiesService = hotelAmenitiesService;
        this.hotelHistogrammService = hotelHistogrammService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        List<HotelResponseDto> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDetailsResponseDto> getHotelById(@PathVariable int id) {
        HotelDetailsResponseDto hotelDetails = hotelService.getHotelById(id);
        return new ResponseEntity<>(hotelDetails, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponseDto>> searchHotels(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String brand,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String country,
                                                       @RequestParam(required = false) String amenities) {
        List<HotelResponseDto> hotels = hotelSearchService.searchHotels(name, brand, city, country, amenities);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelResponseDto> createHotel(@RequestBody HotelCreateRequestDto hotelCreateRequestDto) {
        HotelResponseDto createdHotel = hotelService.createHotel(hotelCreateRequestDto);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Void> addAmenitiesToHotel(@PathVariable int id, @RequestBody List<String> amenities) {
        hotelAmenitiesService.addAmenities(id,  amenities);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<?> getHistogram(@PathVariable String param) {
        Object histogram = hotelHistogrammService.getHistogram(param);
        return new ResponseEntity<>(histogram, HttpStatus.OK);
    }
}
