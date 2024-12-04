package com.example.ecommerce.repository;

import com.example.ecommerce.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleMaster,Integer> {
}
