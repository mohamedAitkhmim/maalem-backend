package com.softmed.maalem.utils;

import com.softmed.maalem.exception.BadRequestException;
import com.softmed.maalem.persistence.entity.User;
import com.softmed.maalem.persistence.repository.UserRepository;
import com.softmed.maalem.presentation.dto.PhotoDto;
import com.softmed.maalem.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    @Override
    public byte[] getProfileImage(UserPrincipal userPrincipal) throws IOException {
        /*if( new File(imgPath+e.getId()+".jpg").exists() )
            return Files.readAllBytes(Paths.get(imgPath+e.getId()+".jpg"));
        else {
            //System.out.println(ClassLoader.getSystemResourceAsStream("/static/0.png"));
            return Files.readAllBytes(Paths.get(imgPath+"0.jpg"));
            // System.out.println(this.getClass().getResource("/resources/static/0.jpg").getFile());
            //return Files.readAllBytes(Paths.get(this.getClass().getResource("/static/0.jpg").getFile()));
        }*/
        return Files.readAllBytes(Paths.get(profileImagesFolder+"/image.jpg"));

        //User user = userRepository.findById(userPrincipal.getId()).orElseThrow(()->new BadRequestException("User introuvable"));
        /*InputStream in = getClass()
                .getResourceAsStream(profileImagesFolder+"/image.jpg");
        return IOUtils.toByteArray(in);

         */
    }

    @Override
    public void saveProfileImage(PhotoDto dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(()->new BadRequestException("User introuvable"));
        if ("PROFILE".equals(dto.getType()))
            user.getProfile().setPhoto(
                    user.getId() + "_" + user.getProfile().getId()+ FilenameUtils.getExtension(dto.getImage().getOriginalFilename())
            );
        System.err.println(user.getProfile().getPhoto());
        /*Etudiant e = etudiantRepo.findById(id).get();
        if(e == null) throw new RuntimeException("Etudiant not exist pour upload image");
        System.out.println("uploading image on "+imgPath);
        Files.write(Paths.get(imgPath+e.getId()+".jpg"),file.getBytes());

         */
    }
}
