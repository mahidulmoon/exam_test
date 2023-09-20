package com.example.mopa.thymeleaf.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmergencyContractUpdateDTO {

    private String institutionType;
    private String telephoneNUmber;
}
