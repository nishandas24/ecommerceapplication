package com.example.ecommerce.repository;

import com.example.ecommerce.model.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserMaster,Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String aLong);

    Optional<Object> findByEmail(String username);
}
