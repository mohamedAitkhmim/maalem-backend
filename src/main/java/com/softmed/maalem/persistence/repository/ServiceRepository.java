package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,String> {
}
