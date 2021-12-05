package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Admin;
import com.softmed.maalem.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
