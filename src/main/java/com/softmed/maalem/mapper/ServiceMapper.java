package com.softmed.maalem.mapper;

import com.softmed.maalem.persistence.entity.Service;
import com.softmed.maalem.presentation.dto.ServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto serviceToDto(Service service);

    @Mapping(source = "latitude",target = "coordonnee.latitude")
    @Mapping(source = "longitude",target = "coordonnee.longitude")
    Service dtoToService(ServiceDto serviceDto);

}
