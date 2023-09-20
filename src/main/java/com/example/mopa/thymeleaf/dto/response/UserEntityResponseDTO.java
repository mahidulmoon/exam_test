package com.example.mopa.thymeleaf.dto.response;

import com.example.mopa.thymeleaf.domain.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserEntityResponseDTO {

    private String id;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
