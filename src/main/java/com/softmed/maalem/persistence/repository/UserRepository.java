package com.softmed.maalem.persistence.repository;

import com.softmed.maalem.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmailAndAccountStatusTrue(String email);
    Optional<User> findByIdAndAccountStatusTrue(String email);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
