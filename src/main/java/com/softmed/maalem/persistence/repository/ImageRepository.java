package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Admin;
import com.softmed.maalem.persistence.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
