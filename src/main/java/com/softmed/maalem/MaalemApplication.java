package com.softmed.maalem;

import com.softmed.maalem.config.AppProperties;
import com.softmed.maalem.mapper.CategorieMapper;
import com.softmed.maalem.persistence.entity.Categorie;
import com.softmed.maalem.presentation.dto.CategorieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MaalemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MaalemApplication.class, args);
    }

    @Autowired
    CategorieMapper categorieMapper;

    @Override
    public void run(String... args) throws Exception {
        Categorie categorie = new Categorie();
        categorie.setLabel("MED");
        CategorieDto dto = categorieMapper.categorieToDto(categorie);
        System.err.println(dto.getLabel());
    }
}
