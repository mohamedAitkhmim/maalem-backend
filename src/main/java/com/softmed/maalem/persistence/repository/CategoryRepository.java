package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categorie,Long> {
}
