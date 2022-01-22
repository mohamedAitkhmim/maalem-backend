package com.softmed.maalem.service;

import com.softmed.maalem.presentation.dto.ProfileDto;
import com.softmed.maalem.presentation.dto.ServiceDto;
import com.softmed.maalem.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface ServicesServiceFace {
    ServiceDto saveService(ServiceDto serviceDto, UserPrincipal userPrincipal) throws IOException;
    List<ServiceDto> getClientServices(UserPrincipal userPrincipal);
}
