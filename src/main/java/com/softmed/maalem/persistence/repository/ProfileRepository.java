package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,String> {
        boolean existsByCnie(String cnie);
}
