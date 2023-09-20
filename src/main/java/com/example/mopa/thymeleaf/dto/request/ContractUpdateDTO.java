package com.example.mopa.thymeleaf.dto.request;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractUpdateDTO {

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
