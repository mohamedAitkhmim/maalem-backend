package com.softmed.maalem.presentation.dto;

import com.softmed.maalem.persistence.entity.Coordonnee;
import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data @ToString
public class ServiceDto {

    @NotBlank
    private String titre;

    private String description;

    private Long latitude;

    private Long longitude;

    @NotBlank
    private String adresse;

    private Long categorieId;

    private List<String> skills;

}
