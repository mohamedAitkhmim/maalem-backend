package com.softmed.maalem;

import com.softmed.maalem.config.AppProperties;
import com.softmed.maalem.mapper.CategorieMapper;
import com.softmed.maalem.persistence.entity.Categorie;
import com.softmed.maalem.presentation.dto.CategorieDto;
import com.softmed.maalem.presentation.dto.RegistrationDto;
import com.softmed.maalem.service.RegistrationFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MaalemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MaalemApplication.class, args);
    }

    @Autowired
    RegistrationFace registrationFace;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("admin@gmail.com");
        dto.setPassword("123456");
        dto.setCnie("G681520");
        dto.setNom("MOHAMED");
        dto.setPrenom("MED");
        dto.setPhone("12345678");
        //registrationFace.register(dto);
        //System.err.println(passwordEncoder.encode("123456"));
    }
}
