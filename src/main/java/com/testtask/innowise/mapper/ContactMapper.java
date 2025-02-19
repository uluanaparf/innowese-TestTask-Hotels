package com.testtask.innowise.mapper;

import com.testtask.innowise.dto.ContactsDto;
import com.testtask.innowise.model.Contacts;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactsDto toDto(Contacts contacts);

    Contacts toEntity(ContactsDto contactsDto);
}
