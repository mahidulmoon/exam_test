package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role extends BaseEntity{
    private String name;
}
