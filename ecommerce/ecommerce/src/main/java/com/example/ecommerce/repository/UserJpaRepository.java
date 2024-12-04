package com.example.ecommerce.repository;

import com.example.ecommerce.model.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserMaster,Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String aLong);
}
