package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.ContractTelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractTelephoneEntity,String> {

    List<ContractTelephoneEntity> findAllByOrderByOrderSlAsc();
}
