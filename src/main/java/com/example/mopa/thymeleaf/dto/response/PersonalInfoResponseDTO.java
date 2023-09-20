package com.example.mopa.thymeleaf.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PersonalInfoResponseDTO {

    private String id;

    private String govId;

    private String employeeName;

    private String designation;

    private String email;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private String prlApplicable;

    private LocalDate prlDate;

    private String mobileNumber;

    private String mobileNumberResidence;

    private String activitiesWork;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String loggedUsers;

    private String comments;

    private String marks;
}
