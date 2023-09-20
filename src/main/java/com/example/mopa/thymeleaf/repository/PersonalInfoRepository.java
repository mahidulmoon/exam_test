package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.PersonalInfoEntity;
import com.example.mopa.thymeleaf.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfoEntity,String> {

    List<PersonalInfoEntity> findAllByLoggedUsers(String userName);

}
