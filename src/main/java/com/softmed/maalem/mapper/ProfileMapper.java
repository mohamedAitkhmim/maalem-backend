package com.softmed.maalem.mapper;

import com.softmed.maalem.persistence.entity.Profile;
import com.softmed.maalem.presentation.dto.ProfileDto;
import com.softmed.maalem.presentation.dto.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile dtoToProfile(RegistrationDto dto);
    Profile dtoToProfile(ProfileDto dto);
    ProfileDto profileToDto(Profile profile);

}
