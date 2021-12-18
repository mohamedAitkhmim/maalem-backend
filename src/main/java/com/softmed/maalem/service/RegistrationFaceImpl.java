package com.softmed.maalem.service;

import com.softmed.maalem.mapper.ProfileMapper;
import com.softmed.maalem.persistence.entity.Role;
import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.persistence.entity.Profile;
import com.softmed.maalem.persistence.repository.ProfileRepository;
import com.softmed.maalem.persistence.repository.UserRepository;
import com.softmed.maalem.presentation.dto.RegistrationDto;
import com.softmed.maalem.utils.UtilsFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class RegistrationFaceImpl implements RegistrationFace {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private UtilsFace utils;

    @Override
    public User register(RegistrationDto registrationDto) {

        //verification d'email
        if ( userRepository.existsByEmail(registrationDto.getEmail()) )
            throw new RuntimeException("email deja existe");

        //verification du cnie
        if ( profileRepository.existsByCnie(registrationDto.getCnie()) )
            throw new RuntimeException("cnie deja existe");

        //save new user
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setAccountStatus(false);
        user.setRole(Role.CLIENT);
        user.setActivationCode(UUID.randomUUID().toString());
        Profile profile = profileMapper.dtoToProfile(registrationDto);
        profile.setDateInscription(new Date());
        user.setProfile(profile);
        user = userRepository.save(user);
        utils.sendActivationMail(user);
        return user;
    }

    @Override
    public void renvoyerActivationCode(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Invalide email"));
        utils.sendActivationMail(user);
    }

    @Override
    public Boolean activerCompte(String id, String code) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Invalide!!!"));
        if (user.getActivationCode().equals(code)){
            user.setAccountStatus(true);
            return true;
        }
        return false ;
    }
}
