package com.example.mopa.thymeleaf.dto.request;

import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import lombok.Data;

@Data
public class RoleRequest {
    private String name;
}
