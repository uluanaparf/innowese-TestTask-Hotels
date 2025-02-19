package com.testtask.innowise.repository;

import com.testtask.innowise.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    List<Hotel> findByNameContainingIgnoreCase(String name);
    List<Hotel> findByBrandContainingIgnoreCase(String brand);
    List<Hotel> findByAddressCityContainingIgnoreCase(String city);
    List<Hotel> findByAddressCountryContainingIgnoreCase(String country);
}
