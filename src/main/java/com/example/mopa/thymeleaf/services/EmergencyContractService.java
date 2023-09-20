package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.EmergencyContractEntity;
import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import com.example.mopa.thymeleaf.dto.request.EmergencyContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.EmergencyContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.request.OrgRequestDTO;
import com.example.mopa.thymeleaf.dto.request.OrgUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.EmergencyContractResponseDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.repository.EmergencyContractRepository;
import com.example.mopa.thymeleaf.repository.OrgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmergencyContractService {

    private final EmergencyContractRepository orgRepository;

    public EmergencyContractRequestDTO createEmergencyContract(EmergencyContractRequestDTO requestDTO) {

        EmergencyContractEntity emergencyContractEntity = new EmergencyContractEntity();
        emergencyContractEntity.setInstitutionType(requestDTO.getInstitutionType());
        emergencyContractEntity.setTelephoneNUmber(requestDTO.getTelephoneNUmber());
        orgRepository.saveAndFlush(emergencyContractEntity);
        return requestDTO;
    }

    public List<EmergencyContractResponseDTO> getAllEmergencyContractList(){

        List<EmergencyContractResponseDTO> contractResponseList = new ArrayList<>();

        List<EmergencyContractEntity> entityList = orgRepository.findAll();

        entityList.forEach(response->{
            EmergencyContractResponseDTO orgResponseDTO = new EmergencyContractResponseDTO();
            orgResponseDTO.setId(response.getId());
            orgResponseDTO.setInstitutionType(response.getInstitutionType());
            orgResponseDTO.setTelephoneNUmber(response.getTelephoneNUmber());
            orgResponseDTO.setCreatedAt(response.getCreatedAt());
            orgResponseDTO.setUpdatedAt(response.getUpdatedAt());

            contractResponseList.add(orgResponseDTO);
        });

        return contractResponseList;
    }

    public EmergencyContractResponseDTO getAllEmergencyContractById(String id){

        EmergencyContractResponseDTO orgResponseDTO = new EmergencyContractResponseDTO();

        Optional<EmergencyContractEntity> optionalEmergencyContract = orgRepository.findById(id);

        EmergencyContractEntity  emergencyContractEntity = optionalEmergencyContract.get();

        orgResponseDTO.setId(emergencyContractEntity.getId());
        orgResponseDTO.setInstitutionType(emergencyContractEntity.getInstitutionType());
        orgResponseDTO.setTelephoneNUmber(emergencyContractEntity.getTelephoneNUmber());
        return orgResponseDTO;
    }

    public void updateEmergencyContractById(String id , EmergencyContractUpdateDTO emergencyContractUpdateDTO){

        Optional<EmergencyContractEntity> optionalEmergencyContract = orgRepository.findById(id);

        EmergencyContractEntity  emergencyContractEntity = optionalEmergencyContract.get();

        emergencyContractEntity.setInstitutionType(emergencyContractUpdateDTO.getInstitutionType());
        emergencyContractEntity.setTelephoneNUmber(emergencyContractUpdateDTO.getTelephoneNUmber());

        orgRepository.save(emergencyContractEntity);

    }

    public void deleteById(String id) {
        Optional<EmergencyContractEntity> optionalEmergencyContract = orgRepository.findById(id);
        EmergencyContractEntity  emergencyContractEntity = optionalEmergencyContract.get();
        orgRepository.delete(emergencyContractEntity);
    }


}

