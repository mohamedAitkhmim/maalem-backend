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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/client/account")
public class AccountController {

    @Autowired
    private RegistrationFace registrationService;

    @Autowired
    private ProfileServiceFace profileService;

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
        return ResponseEntity.status(HttpStatus.OK).body(profileService.saveUserProfile(dto,userPrincipal));
    }

    @PostMapping("/saveImage/{type}")
    public ResponseEntity<ApiResponse> saveImageProfile(@ModelAttribute MultipartFile image,@PathVariable String type,@CurrentUser UserPrincipal userPrincipal) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true,profileService.saveProfileImage(image,type,userPrincipal))
        );
    }


}
