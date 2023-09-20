package com.example.mopa.thymeleaf.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrgResponseDTO {

    private String id;

    private String OrgType;

    private String orgName;

    private String orgLocation;

    private String orgAddress;

    private String orgEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
