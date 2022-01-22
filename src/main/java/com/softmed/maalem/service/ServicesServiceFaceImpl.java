package com.softmed.maalem.service;

import com.softmed.maalem.exception.BadRequestException;
import com.softmed.maalem.mapper.ServiceMapper;
import com.softmed.maalem.persistence.entity.Client;
import com.softmed.maalem.persistence.entity.Image;
import com.softmed.maalem.persistence.entity.Service;
import com.softmed.maalem.persistence.repository.CategoryRepository;
import com.softmed.maalem.persistence.repository.ClientRepository;
import com.softmed.maalem.persistence.repository.ImageRepository;
import com.softmed.maalem.persistence.repository.ServiceRepository;
import com.softmed.maalem.presentation.dto.ServiceDto;
import com.softmed.maalem.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Autowired
    private ImageRepository imageRepository;

    @Value("${serviceImagesFolder}")
    private String serviceImagesFolder;

    @Override
    public ServiceDto saveService(ServiceDto serviceDto, UserPrincipal userPrincipal) throws IOException {
        Client client = clientRepository.findById(userPrincipal.getId())
                .orElseThrow(()->new BadRequestException("Client Introuvable"));
        Service service = serviceMapper.dtoToService(serviceDto);
        service.setId(UUID.randomUUID().toString());
        service.setCategorie(
                categoryRepository.findById(serviceDto.getCategorieId()).
                        orElseThrow(()->new BadRequestException("categorie introuvable"))
        );
        service.setClient(client);
        service = serviceRepository.save(service);
        //save images
        if (serviceDto.getImages().size() == 0) return serviceDto;
        List<Image> images = new ArrayList<>();
        Image img ;
        for (MultipartFile file:serviceDto.getImages()) {
            images.add(
                    img =new Image(
                            null,
                            UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(file.getOriginalFilename()),
                            service
                    )
            );
            file.transferTo(Paths.get(serviceImagesFolder+"/"+img.getNom()));
        }
      imageRepository.saveAll(images);
        return serviceDto;
    }

    @Override
    public List<ServiceDto> getClientServices(UserPrincipal userPrincipal) {
        List<Service> services = serviceRepository.findAllByClient_Id(userPrincipal.getId());
        List<ServiceDto> serviceDtos = serviceMapper.mapToListDto(services);
        return serviceDtos;
    }

}
