package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.ContractTelephoneEntity;
import com.example.mopa.thymeleaf.dto.request.ContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.ContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.ContractResponseDTO;
import com.example.mopa.thymeleaf.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractRequestDTO createContract(ContractRequestDTO requestDTO) {

        ContractTelephoneEntity contractEntity = new ContractTelephoneEntity();
        BeanUtils.copyProperties(requestDTO,contractEntity);
        contractRepository.saveAndFlush(contractEntity);
        return requestDTO;
    }

    public List<ContractResponseDTO> getAllContractList(){

        List<ContractResponseDTO> responseDTOList = new ArrayList<>();

        List<ContractTelephoneEntity> entityList = contractRepository.findAllByOrderByOrderSlAsc();

        List<ContractResponseDTO> finalResponseDTOList = responseDTOList;

        entityList.forEach(response->{
            ContractResponseDTO orgResponseDTO = new ContractResponseDTO();
            orgResponseDTO.setId(response.getId());
            orgResponseDTO.setOrganization(response.getOrganization());
            orgResponseDTO.setGovId(response.getGovId());
            orgResponseDTO.setName(response.getName());
            orgResponseDTO.setDesignation(response.getDesignation());
            orgResponseDTO.setMobileNumber(response.getMobileNumber());
            orgResponseDTO.setEmail(response.getEmail());
            orgResponseDTO.setOrderSl(response.getOrderSl());
            orgResponseDTO.setCreatedAt(response.getCreatedAt());
            orgResponseDTO.setUpdatedAt(response.getUpdatedAt());

            finalResponseDTOList.add(orgResponseDTO);
        });


        responseDTOList = finalResponseDTOList.stream().sorted((o1, o2)->o1.getOrderSl().
                        compareTo(o2.getOrderSl())).
                collect(Collectors.toList());

        return responseDTOList;
    }




    public ContractResponseDTO getAllContractById(String id){

        ContractResponseDTO orgResponseList = new ContractResponseDTO();

        Optional<ContractTelephoneEntity> optionalOrganizationResponse = contractRepository.findById(id);

        ContractTelephoneEntity  entity = optionalOrganizationResponse.get();

        orgResponseList.setId(entity.getId());
        orgResponseList.setOrganization(entity.getOrganization());
        orgResponseList.setGovId(entity.getGovId());
        orgResponseList.setName(entity.getName());
        orgResponseList.setDesignation(entity.getDesignation());
        orgResponseList.setMobileNumber(entity.getMobileNumber());
        orgResponseList.setEmail(entity.getEmail());
        orgResponseList.setIntercomOffice(entity.getIntercomOffice());
        orgResponseList.setIntercomResidence(entity.getIntercomResidence());
        orgResponseList.setMobileNumberResidence(entity.getMobileNumberResidence());
        orgResponseList.setOrderSl(entity.getOrderSl());

        return orgResponseList;
    }

    public void updateOrganizationById(String id, ContractUpdateDTO contractUpdateDTO) {

        Optional<ContractTelephoneEntity> optionalOrganizationResponse = contractRepository.findById(id);

        ContractTelephoneEntity  entity = optionalOrganizationResponse.get();
        entity.setOrganization(contractUpdateDTO.getOrganization());
        entity.setGovId(contractUpdateDTO.getGovId());
        entity.setName(contractUpdateDTO.getName());
        entity.setDesignation(contractUpdateDTO.getDesignation());
        entity.setMobileNumber(contractUpdateDTO.getMobileNumber());
        entity.setEmail(contractUpdateDTO.getEmail());
        entity.setIntercomOffice(contractUpdateDTO.getIntercomOffice());
        entity.setIntercomResidence(contractUpdateDTO.getIntercomResidence());
        entity.setMobileNumberResidence(contractUpdateDTO.getMobileNumberResidence());
        entity.setOrderSl(contractUpdateDTO.getOrderSl());

        contractRepository.save(entity);

    }

    public void deleteById(String id) {
        Optional<ContractTelephoneEntity> optionalOrganizationResponse = contractRepository.findById(id);
        ContractTelephoneEntity organization = optionalOrganizationResponse.get();
        contractRepository.delete(organization);
    }


}

