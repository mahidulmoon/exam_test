package com.example.mopa.thymeleaf.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class LoggedUserDetailsResponse {
    private String userName;

    private List<String> userRole;

    private Boolean isAuthenticated;
}
