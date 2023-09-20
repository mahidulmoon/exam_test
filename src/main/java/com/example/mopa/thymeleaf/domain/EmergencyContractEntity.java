package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class EmergencyContractEntity extends BaseEntity{

    private String institutionType;

    private String telephoneNUmber;

}
