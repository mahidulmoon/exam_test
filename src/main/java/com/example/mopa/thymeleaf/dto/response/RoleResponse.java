package com.example.mopa.thymeleaf.dto.response;

import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleResponse {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
