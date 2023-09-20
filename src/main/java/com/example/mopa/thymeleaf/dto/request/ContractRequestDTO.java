package com.example.mopa.thymeleaf.dto.request;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import lombok.Data;

@Data
public class ContractRequestDTO {

    private String organizationId;

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
}
