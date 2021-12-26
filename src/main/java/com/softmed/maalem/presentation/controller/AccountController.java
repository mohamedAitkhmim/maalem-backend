package com.softmed.maalem.presentation.controller;

import com.softmed.maalem.presentation.dto.*;
import com.softmed.maalem.security.CurrentUser;
import com.softmed.maalem.security.UserPrincipal;
import com.softmed.maalem.service.ProfileServiceFace;
import com.softmed.maalem.service.RegistrationFace;
import com.softmed.maalem.utils.UtilsFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/client/account")
public class AccountController {

    @Autowired
    private RegistrationFace registrationService;

    @Autowired
    private ProfileServiceFace profileService;

    @Autowired
    private UtilsFace utils;

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordResetDto dto,@CurrentUser UserPrincipal userPrincipal) {
        registrationService.resetPassword(dto,userPrincipal);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"password modifié"));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getUserProfile(userPrincipal));
    }

    @PostMapping("/saveProfile")
    public ResponseEntity<ProfileDto> saveProfile(@Valid @RequestBody ProfileDto dto,@CurrentUser UserPrincipal userPrincipal) {
        System.err.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(profileService.saveUserProfile(dto,userPrincipal));
    }

    @GetMapping(value = "/image-profile",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProfileImage(@CurrentUser UserPrincipal userPrincipal) throws IOException {
        return utils.getProfileImage(userPrincipal);
    }

    @PostMapping("/saveImage")
    public ResponseEntity<ApiResponse> saveImageProfile(@Valid @ModelAttribute PhotoDto dto) {
        System.err.println(dto.getId());
        System.err.println(dto.getType());
        utils.saveProfileImage(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"image saved"));
    }


}
