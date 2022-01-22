package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service,String> {
    List<Service> findAllByClient_Id(String clientId);
}
