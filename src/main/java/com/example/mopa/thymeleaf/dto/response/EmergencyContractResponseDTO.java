package com.example.mopa.thymeleaf.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmergencyContractResponseDTO {
    private String id;
    private String institutionType;
    private String telephoneNUmber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
