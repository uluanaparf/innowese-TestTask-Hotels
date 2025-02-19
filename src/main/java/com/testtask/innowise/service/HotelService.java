package com.testtask.innowise.service;

import com.testtask.innowise.dto.request.HotelCreateRequestDto;
import com.testtask.innowise.dto.response.HotelDetailsResponseDto;
import com.testtask.innowise.dto.response.HotelResponseDto;
import com.testtask.innowise.mapper.HotelMapper;
import com.testtask.innowise.model.Hotel;
import com.testtask.innowise.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private  HotelRepository hotelRepository;

    @Autowired
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Transactional(readOnly = true)
    public List<HotelResponseDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HotelDetailsResponseDto getHotelById(int id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        return hotelMapper.toDetailsResponseDto(hotel);
    }

    @Transactional
    public HotelResponseDto createHotel(HotelCreateRequestDto hotelCreateRequestDto) {
        Hotel hotel = hotelMapper.toEntity(hotelCreateRequestDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseDto(savedHotel);
    }
}