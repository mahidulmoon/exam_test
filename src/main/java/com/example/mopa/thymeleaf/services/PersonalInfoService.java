package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.Utils.AuthLoggedUser;
import com.example.mopa.thymeleaf.domain.PersonalInfoEntity;
import com.example.mopa.thymeleaf.domain.Role;
import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.dto.request.PersonalInfoRequestDTO;
import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.response.PersonalInfoResponseDTO;
import com.example.mopa.thymeleaf.repository.PersonalInfoRepository;
import com.example.mopa.thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonalInfoService {

    private final PersonalInfoRepository repository;
    private final AuthLoggedUser authLoggedUser;
    private final UserRepository userRepository;

    public PersonalInfoRequestDTO createPersonalInfo(PersonalInfoRequestDTO requestDTO) {
        PersonalInfoEntity personalInfoEntity = new PersonalInfoEntity();
        BeanUtils.copyProperties(requestDTO, personalInfoEntity);
        personalInfoEntity.setLoggedUsers(authLoggedUser.getLoggedAuthUser());
        repository.saveAndFlush(personalInfoEntity);
        return requestDTO;
    }


    public List<PersonalInfoResponseDTO> getAllInformation() {

        List<PersonalInfoResponseDTO> list = new ArrayList<>();

        List<PersonalInfoEntity> entityList = new ArrayList<>();
        entityList = repository.findAllByLoggedUsers(authLoggedUser.getLoggedAuthUser());

        List<UserEntity> userEntities = new ArrayList<>();
        userEntities = userRepository.findAllByUsername(authLoggedUser.getLoggedAuthUser());
        if (userEntities.get(0).getRoles().iterator().next().getName().toString().equals("ADMIN")) {
            entityList = new ArrayList<>();
            entityList = repository.findAll();
        }

        entityList.forEach(responseList -> {
            PersonalInfoResponseDTO personalInfoResponseDTO = new PersonalInfoResponseDTO();
            personalInfoResponseDTO.setId(responseList.getId());
            personalInfoResponseDTO.setGovId(responseList.getGovId());
            personalInfoResponseDTO.setEmployeeName(responseList.getEmployeeName());
            personalInfoResponseDTO.setEmail(responseList.getEmail());
            personalInfoResponseDTO.setDateOfBirth(responseList.getDateOfBirth());
            personalInfoResponseDTO.setActivitiesWork(responseList.getActivitiesWork());
            personalInfoResponseDTO.setDateOfJoining(responseList.getDateOfJoining());
            personalInfoResponseDTO.setMobileNumber(responseList.getMobileNumber());
            personalInfoResponseDTO.setMobileNumberResidence(responseList.getMobileNumberResidence());
            personalInfoResponseDTO.setDesignation(responseList.getDesignation());
            personalInfoResponseDTO.setCreatedAt(responseList.getCreatedAt());
            personalInfoResponseDTO.setLoggedUsers(responseList.getLoggedUsers());

            LocalDate todayDate = LocalDate.now();
            LocalDate dateOfBirthDayDate = responseList.getDateOfBirth();
            Long range = ChronoUnit.YEARS.between(dateOfBirthDayDate, todayDate);
            if (range >= 59) {
                LocalDate prlDate = dateOfBirthDayDate.plusYears(59);
                personalInfoResponseDTO.setPrlApplicable("YES");
                personalInfoResponseDTO.setPrlDate(prlDate);
            } else {
                LocalDate prlDate = dateOfBirthDayDate.plusYears(59);
                personalInfoResponseDTO.setPrlApplicable("NO");
                personalInfoResponseDTO.setPrlDate(prlDate);
            }


            list.add(personalInfoResponseDTO);
        });
        return list;
    }

    public List<PersonalInfoResponseDTO> getAllPRLInformation() {

        List<PersonalInfoResponseDTO> list = new ArrayList<>();

        List<PersonalInfoEntity> entityList = new ArrayList<>();
        entityList = repository.findAll();

        for (PersonalInfoEntity responseList : entityList){
            LocalDate todayDate = LocalDate.now();
            LocalDate dateOfBirthDayDate = responseList.getDateOfBirth();
            PersonalInfoResponseDTO personalInfoResponseDTO = new PersonalInfoResponseDTO();
            personalInfoResponseDTO.setId(responseList.getId());
            personalInfoResponseDTO.setGovId(responseList.getGovId());
            personalInfoResponseDTO.setEmployeeName(responseList.getEmployeeName());
            personalInfoResponseDTO.setEmail(responseList.getEmail());
            personalInfoResponseDTO.setDateOfBirth(responseList.getDateOfBirth());
            personalInfoResponseDTO.setDateOfJoining(responseList.getDateOfJoining());
            personalInfoResponseDTO.setActivitiesWork(responseList.getActivitiesWork());
            personalInfoResponseDTO.setMobileNumber(responseList.getMobileNumber());
            personalInfoResponseDTO.setMobileNumberResidence(responseList.getMobileNumberResidence());
            personalInfoResponseDTO.setDesignation(responseList.getDesignation());
            personalInfoResponseDTO.setCreatedAt(responseList.getCreatedAt());
            personalInfoResponseDTO.setLoggedUsers(responseList.getLoggedUsers());
            LocalDate prlDate = dateOfBirthDayDate.plusYears(59);
            personalInfoResponseDTO.setPrlApplicable("YES");
            personalInfoResponseDTO.setPrlDate(prlDate);
            personalInfoResponseDTO.setMarks(responseList.getMarks());
            personalInfoResponseDTO.setComments(responseList.getComments());
            list.add(personalInfoResponseDTO);
        }


        return list;
    }

    public PersonalInfoResponseDTO getPersonalInfoBy(String id){
        Optional<PersonalInfoEntity> optionalPersonalInfoEntity = repository.findById(id);
        if (!optionalPersonalInfoEntity.isPresent()){

        }
        PersonalInfoEntity personalInfoEntity = optionalPersonalInfoEntity.get();

        PersonalInfoResponseDTO personalInfoResponseDTO = new PersonalInfoResponseDTO();

        personalInfoResponseDTO.setId(personalInfoEntity.getId());
        personalInfoResponseDTO.setGovId(personalInfoEntity.getGovId());
        personalInfoResponseDTO.setEmployeeName(personalInfoEntity.getEmployeeName());
        personalInfoResponseDTO.setDesignation(personalInfoEntity.getDesignation());
        personalInfoResponseDTO.setEmail(personalInfoEntity.getEmail());
        personalInfoResponseDTO.setDateOfBirth(personalInfoEntity.getDateOfBirth());
        personalInfoResponseDTO.setDateOfJoining(personalInfoEntity.getDateOfJoining());
        personalInfoResponseDTO.setMobileNumberResidence(personalInfoEntity.getMobileNumberResidence());
        personalInfoResponseDTO.setMobileNumber(personalInfoEntity.getMobileNumber());

        return personalInfoResponseDTO;

    }

    public void updatePersonalInfoBy(String id, PersonalInfoRequestDTO requestDTO) {

        Optional<PersonalInfoEntity> optionalPersonalInfoEntity = repository.findById(id);
        if (!optionalPersonalInfoEntity.isPresent()){
            throw new RuntimeException("Not Found");
        }

        PersonalInfoEntity personalInfoEntity = optionalPersonalInfoEntity.get();
        personalInfoEntity.setGovId(requestDTO.getGovId());
        personalInfoEntity.setEmployeeName(requestDTO.getEmployeeName());
        personalInfoEntity.setDesignation(requestDTO.getDesignation());
        personalInfoEntity.setEmail(requestDTO.getEmail());
        personalInfoEntity.setDateOfBirth(requestDTO.getDateOfBirth());
        personalInfoEntity.setDateOfJoining(requestDTO.getDateOfJoining());
        personalInfoEntity.setMobileNumber(requestDTO.getMobileNumber());
        personalInfoEntity.setMobileNumberResidence(requestDTO.getMobileNumberResidence());
        personalInfoEntity.setComments(requestDTO.getComments());
        personalInfoEntity.setMarks(requestDTO.getMarks());
        repository.save(personalInfoEntity);
    }
}

