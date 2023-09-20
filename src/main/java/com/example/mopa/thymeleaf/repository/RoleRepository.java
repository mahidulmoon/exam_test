package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import com.example.mopa.thymeleaf.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    List<Role> findAllByOrderByIdDesc();
    boolean existsByName(String roleName);
}
