package com.softmed.maalem.service;

import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.presentation.dto.RegistrationDto;

public interface RegistrationFace {
    User register(RegistrationDto registrationDto);
    void renvoyerActivationCode(String email);
    Boolean activerCompte(String id,String code);
}
