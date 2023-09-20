package com.example.mopa.thymeleaf.dto.request;

import com.example.mopa.thymeleaf.domain.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserEntityRequestDTO {

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;

}
