package com.example.mopa.thymeleaf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class PersonalInfoEntity extends BaseEntity {

    private String govId;

    private String employeeName;

    private String designation ;

    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfJoining;

    private String mobileNumber;

    private String mobileNumberResidence;

    private String activitiesWork;

    private String loggedUsers;

    private String comments;

    private String marks;


}
