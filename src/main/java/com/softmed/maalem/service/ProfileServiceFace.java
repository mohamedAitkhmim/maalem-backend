package com.softmed.maalem.service;

import com.softmed.maalem.presentation.dto.ProfileDto;
import com.softmed.maalem.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileServiceFace {
    ProfileDto getUserProfile(UserPrincipal userPrincipal);
    ProfileDto saveUserProfile(ProfileDto profileDto,UserPrincipal userPrincipal);
    String saveProfileImage(MultipartFile image,String type,UserPrincipal userPrincipal) throws IOException;
    byte[] getProfileImage(String img,String type) throws IOException;
}
