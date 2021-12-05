package com.softmed.maalem.mapper;

import com.softmed.maalem.persistence.entity.Profile;
import com.softmed.maalem.presentation.dto.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    //RegistrationDto ProfileToDto(Profile profile);

    Profile dtoToProfile(RegistrationDto dto);
}
