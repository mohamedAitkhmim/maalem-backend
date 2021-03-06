package com.softmed.maalem.presentation.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softmed.maalem.presentation.dto.*;
import com.softmed.maalem.security.CurrentUser;
import com.softmed.maalem.security.UserPrincipal;
import com.softmed.maalem.service.CategorieFace;
import com.softmed.maalem.service.ProfileServiceFace;
import com.softmed.maalem.service.RegistrationFace;
import com.softmed.maalem.service.ServicesServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client/service")
public class ServiceController {

    @Autowired
    private ServicesServiceFace servicesService;

    @Autowired
    private CategorieFace categorieService;

    @PostMapping(value = "/save")
    public ResponseEntity<ServiceDto> saveService(@ModelAttribute ServiceDto dto,@CurrentUser UserPrincipal userPrincipal) throws IOException {
        System.err.println(dto.toString());
        if (dto.getImages().size() > 0)
            dto.getImages().forEach(file -> {
                System.err.println(file.getOriginalFilename());
            });
        //return ResponseEntity.status(HttpStatus.OK).body("OK");
        return ResponseEntity.status(HttpStatus.OK).body(servicesService.saveService(dto,userPrincipal));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDto>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categorieService.getAllCategories());
    }

    @GetMapping("/client/all")
    public ResponseEntity<List<ServiceDto>> getServiceByClient(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.status(HttpStatus.OK).body(servicesService.getClientServices(userPrincipal));
    }
}
