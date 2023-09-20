package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class ContractTelephoneEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn
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
