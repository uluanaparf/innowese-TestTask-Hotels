package com.testtask.innowise.mapper;

import com.testtask.innowise.dto.AddresDto;
import com.testtask.innowise.model.Addres;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddresMapper {

    AddresDto toDto(Addres addres);

    Addres toEntity(AddresDto addresDto);
}
