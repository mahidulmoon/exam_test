package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findAllByEmail(String email);

    @Query("SELECT u from UserEntity u Where u.username = :username")
    public UserEntity getUserByUsername(@Param("username") String username);

    List<UserEntity> findAllByUsername(String userName);
}
