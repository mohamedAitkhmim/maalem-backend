package com.softmed.maalem.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter @ToString
public class PasswordResetDto {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

}
