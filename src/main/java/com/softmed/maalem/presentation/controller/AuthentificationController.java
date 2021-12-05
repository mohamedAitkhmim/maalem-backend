package com.softmed.maalem.presentation.controller;

import com.softmed.maalem.presentation.dto.AuthResponse;
import com.softmed.maalem.presentation.dto.LoginRequest;
import com.softmed.maalem.presentation.dto.RegistrationDto;
import com.softmed.maalem.security.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        LOGGER.info("new cnx of client : "+loginRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDto registration) {

        return ResponseEntity.ok("");
    }

    /*@PostMapping("/admin/login")
    public ResponseEntity<?> authenticateEtudiant(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());
        if (!userRepository.isStudent(loginRequest.getUsername()))
            return ResponseEntity.ok(null);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        LOGGER.info("new cnx of "+loginRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }*/


}
