package com.softmed.maalem.presentation.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PhotoDto {

    @NotBlank
    private String id;

    @NotNull
    private MultipartFile image;

    @NotBlank
    private String type;

}
