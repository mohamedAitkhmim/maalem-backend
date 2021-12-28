package com.softmed.maalem.presentation.controller;

import com.softmed.maalem.presentation.dto.*;
import com.softmed.maalem.security.CurrentUser;
import com.softmed.maalem.security.UserPrincipal;
import com.softmed.maalem.service.CategorieFace;
import com.softmed.maalem.service.ProfileServiceFace;
import com.softmed.maalem.service.RegistrationFace;
import com.softmed.maalem.service.ServicesServiceFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client/service")
public class ServiceController {

    @Autowired
    private ServicesServiceFace servicesService;

    @Autowired
    private CategorieFace categorieService;

    @PostMapping("/save")
    public ResponseEntity<ServiceDto> saveService(@Valid @RequestBody ServiceDto dto, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.status(HttpStatus.OK).body(servicesService.saveService(dto,userPrincipal));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDto>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categorieService.getAllCategories());
    }
}
