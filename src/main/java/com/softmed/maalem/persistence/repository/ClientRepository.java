package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Admin;
import com.softmed.maalem.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {
}
