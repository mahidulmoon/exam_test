package com.example.mopa.thymeleaf.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PersonalInfoRequestDTO {

    private String govId;

    private String employeeName;

    private String designation ;

    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfJoining;

    private String mobileNumber;

    private String activitiesWork;

    private String mobileNumberResidence;

    private String loggedUsers;

    private String marks;

    private String comments;
}
