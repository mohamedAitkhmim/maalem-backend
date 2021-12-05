package com.softmed.maalem.mapper;

import com.softmed.maalem.persistence.entity.Categorie;
import com.softmed.maalem.presentation.dto.CategorieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDto categorieToDto(Categorie categorie);
    Categorie dtoTocategorie(CategorieDto dto);
}
