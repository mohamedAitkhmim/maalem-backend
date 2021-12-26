package com.softmed.maalem.presentation.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data @ToString
public class ProfileDto {

    private String userId;

    private String email;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String phone;

    @NotBlank
    private String cnie;

    private String facebook;

    private String linkedin;

    private String github;

    private Date dateInscription;

    private String about;

    private String ville;

    private String adresse;
}
