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
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

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

    @Value("${profileImagesFolder}")
    private String profileImagesFolder;

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

    @Override
    public void saveProfileImage(MultipartFile image, String type,UserPrincipal userPrincipal) throws IOException {
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow(()->new BadRequestException("User introuvable"));
        if (image == null || image.isEmpty()) throw new RuntimeException("image null");

        switch (type){
            case "PROFILE":{
                user.getProfile().setPhoto(UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(image.getOriginalFilename());
                image.transferTo(Paths.get(profileImagesFolder+user.getProfile().getPhoto()));
                break;
            }
        }
    }
}
