package com.softmed.maalem.mapper;

import com.softmed.maalem.persistence.entity.Categorie;
import com.softmed.maalem.presentation.dto.CategorieDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDto mapToDto(Categorie categorie);
    Categorie mapTocategorie(CategorieDto dto);

    List<CategorieDto> mapToListDto(List<Categorie> categorieList);
}
