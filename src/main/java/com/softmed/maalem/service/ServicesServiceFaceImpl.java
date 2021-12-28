package com.softmed.maalem.service;

import com.softmed.maalem.exception.BadRequestException;
import com.softmed.maalem.mapper.ServiceMapper;
import com.softmed.maalem.persistence.entity.Client;
import com.softmed.maalem.persistence.entity.Service;
import com.softmed.maalem.persistence.repository.CategoryRepository;
import com.softmed.maalem.persistence.repository.ClientRepository;
import com.softmed.maalem.persistence.repository.ServiceRepository;
import com.softmed.maalem.presentation.dto.ServiceDto;
import com.softmed.maalem.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class ServicesServiceFaceImpl implements ServicesServiceFace {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ServiceDto saveService(ServiceDto serviceDto, UserPrincipal userPrincipal) {
        Client client = clientRepository.findById(userPrincipal.getId())
                .orElseThrow(()->new BadRequestException("Client Introuvable"));
        Service service = serviceMapper.dtoToService(serviceDto);
        service.setId(UUID.randomUUID().toString());
        service.setCategorie(
                categoryRepository.findById(serviceDto.getCategorieId()).
                        orElseThrow(()->new BadRequestException("categorie introuvable"))
        );
        service.setClient(client);
        serviceRepository.save(service);
        return serviceDto;
    }

}
