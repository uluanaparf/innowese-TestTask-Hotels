package com.testtask.innowise.mapper;

import com.testtask.innowise.dto.ArrivalTimeDto;
import com.testtask.innowise.model.ArrivalTime;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArrivalTimeMapper {

    ArrivalTimeDto toDto(ArrivalTime arrivalTime);

    ArrivalTime toEntity(ArrivalTimeDto arrivalTimeDto);
}
