package com.softmed.maalem.utils;

import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.presentation.dto.PhotoDto;
import com.softmed.maalem.security.UserPrincipal;

import java.io.IOException;

public interface UtilsFace {
    Boolean sendActivationMail(User user);
    byte[] getProfileImage(UserPrincipal userPrincipal) throws IOException;
    void saveProfileImage(PhotoDto dto);

}
