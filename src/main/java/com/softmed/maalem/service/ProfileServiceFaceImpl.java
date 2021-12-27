package com.softmed.maalem.service;

import com.softmed.maalem.exception.BadRequestException;
import com.softmed.maalem.mapper.ProfileMapper;
import com.softmed.maalem.persistence.entity.Profile;
import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.persistence.repository.ProfileRepository;
import com.softmed.maalem.persistence.repository.UserRepository;
import com.softmed.maalem.presentation.dto.ProfileDto;
import com.softmed.maalem.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ProfileServiceFaceImpl implements ProfileServiceFace {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public ProfileDto getUserProfile(UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow(()->new BadRequestException("User introuvable"));
        ProfileDto profileDto = profileMapper.profileToDto(user.getProfile());
        profileDto.setEmail(user.getEmail());
        profileDto.setUserId(user.getId());
        return profileDto;
    }

    @Override
    public ProfileDto saveUserProfile(ProfileDto profileDto, UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow(()->new BadRequestException("User introuvable"));
        Profile profile = profileMapper.dtoToProfile(profileDto);
        profile.setDateInscription(user.getProfile().getDateInscription());
        profile.setId(user.getProfile().getId());
        profile.setPhoto(user.getProfile().getPhoto());
        profile.setBackground(user.getProfile().getBackground());
        Profile p = profileRepository.save(profile);
        profileDto = profileMapper.profileToDto(p);
        return profileDto;
    }
}
