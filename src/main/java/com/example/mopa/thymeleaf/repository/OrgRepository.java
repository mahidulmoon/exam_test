package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<OrganizationEntity,String> {
}
