package com.softmed.maalem.service;

import com.softmed.maalem.exception.BadRequestException;
import com.softmed.maalem.mapper.ProfileMapper;
import com.softmed.maalem.persistence.entity.Client;
import com.softmed.maalem.persistence.entity.Role;
import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.persistence.entity.Profile;
import com.softmed.maalem.persistence.repository.ClientRepository;
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

    //@Autowired
    //private ClientRepository clientRepository;

    @Override
    public Client register(RegistrationDto registrationDto) {

        //verification d'email
        if ( userRepository.existsByEmail(registrationDto.getEmail()) )
            throw new BadRequestException("email deja existe");

        //verification du cnie
        if ( profileRepository.existsByCnie(registrationDto.getCnie()) )
            throw new BadRequestException("cnie deja existe");

        //save new user
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setEmail(registrationDto.getEmail());
        client.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        client.setAccountStatus(false);
        client.setRole(Role.CLIENT);
        client.setActivationCode(UUID.randomUUID().toString());
        Profile profile = profileMapper.dtoToProfile(registrationDto);
        profile.setDateInscription(new Date());
        client.setProfile(profile);
        client = userRepository.save(client);
        //utils.sendActivationMail(client);
        return client;
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
