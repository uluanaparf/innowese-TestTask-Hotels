package com.testtask.innowise.mapper;

import com.testtask.innowise.dto.AddressDto;
import com.testtask.innowise.model.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
