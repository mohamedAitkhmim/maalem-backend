package com.softmed.maalem.utils;

import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtilsFaceImpl implements UtilsFace {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${serverAdresse}")
    private String adresse;

    @Value("${profileImagesFolder}")
    private String profileImagesFolder;



    @Override
    public Boolean sendActivationMail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("MAALEM <code activation>");
        message.setText("cliquer sur le lien suivant pour activer votre compte :"+
                adresse+user.getActivationCode());

        try{
            emailSender.send(message);
        }
        catch (Exception e){
            log.error("email not valid");
            log.error(e.getMessage());
            throw new RuntimeException("email not valid");
        }
        return true;
    }


}
