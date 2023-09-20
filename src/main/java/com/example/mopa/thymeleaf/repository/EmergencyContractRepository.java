package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.EmergencyContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContractRepository extends JpaRepository<EmergencyContractEntity, String> {
}
