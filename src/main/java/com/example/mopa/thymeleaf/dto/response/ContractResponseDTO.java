package com.example.mopa.thymeleaf.dto.response;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractResponseDTO {

    private String id;

    private OrganizationEntity organization;

    private String govId;

    private String name;

    private String designation ;

    private String email;

    private String intercomOffice;

    private String intercomResidence;

    private String mobileNumber;

    private String mobileNumberResidence;

    private Integer orderSl;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
