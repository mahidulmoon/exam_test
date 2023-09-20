package com.example.mopa.thymeleaf.dto.response;

import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginCredResponse {
    private String id;
    private UserEntity userEntity;
    private RoleName roleName;
    private String roleCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
