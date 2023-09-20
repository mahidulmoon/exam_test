package com.example.mopa.thymeleaf.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrgUpdateDTO {

    private String OrgType;

    private String orgName;

    private String orgLocation;

    private String orgAddress;

    private String orgEmail;
}
