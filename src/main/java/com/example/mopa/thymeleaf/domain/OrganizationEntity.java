package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class OrganizationEntity extends BaseEntity{

    private String OrgType;

    private String orgName;

    private String orgLocation;

    private String orgAddress;

    private String orgEmail;
}
