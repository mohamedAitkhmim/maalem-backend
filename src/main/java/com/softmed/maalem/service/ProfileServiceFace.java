package com.softmed.maalem.service;

import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.presentation.dto.ProfileDto;
import com.softmed.maalem.security.UserPrincipal;

public interface ProfileServiceFace {
    ProfileDto getUserProfile(UserPrincipal userPrincipal);
    ProfileDto saveUserProfile(ProfileDto profileDto,UserPrincipal userPrincipal);
}
