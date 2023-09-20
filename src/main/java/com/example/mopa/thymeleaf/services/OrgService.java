package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import com.example.mopa.thymeleaf.dto.request.OrgRequestDTO;
import com.example.mopa.thymeleaf.dto.request.OrgUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.repository.OrgRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrgService {

    private final OrgRepository orgRepository;

    public OrgRequestDTO createOrg(OrgRequestDTO requestDTO) {

        OrganizationEntity organizationEntity = new OrganizationEntity();

        organizationEntity.setOrgType(requestDTO.getOrgType());
        organizationEntity.setOrgName(requestDTO.getOrgName());
        organizationEntity.setOrgLocation(requestDTO.getOrgLocation());
        organizationEntity.setOrgAddress(requestDTO.getOrgAddress());
        organizationEntity.setOrgEmail(requestDTO.getOrgEmail());
        orgRepository.saveAndFlush(organizationEntity);
        return requestDTO;
    }

    public List<OrgResponseDTO> getAllOrgList(){

        List<OrgResponseDTO> orgResponseDTOList = new ArrayList<>();

        List<OrganizationEntity> entityList = orgRepository.findAll();

        entityList.forEach(response->{
            OrgResponseDTO orgResponseDTO = new OrgResponseDTO();
            orgResponseDTO.setId(response.getId());
            orgResponseDTO.setOrgType(response.getOrgType());
            orgResponseDTO.setOrgName(response.getOrgName());
            orgResponseDTO.setOrgLocation(response.getOrgLocation());
            orgResponseDTO.setOrgAddress(response.getOrgAddress());
            orgResponseDTO.setCreatedAt(response.getCreatedAt());
            orgResponseDTO.setUpdatedAt(response.getUpdatedAt());

            orgResponseDTOList.add(orgResponseDTO);
        });

        return orgResponseDTOList;
    }

    public OrgResponseDTO getAllOrgById(String id){

        OrgResponseDTO orgResponseDTO = new OrgResponseDTO();

        Optional<OrganizationEntity> optionalOrganizationResponse = orgRepository.findById(id);

        OrganizationEntity  organization = optionalOrganizationResponse.get();

        orgResponseDTO.setId(organization.getId());
        orgResponseDTO.setOrgType(organization.getOrgType());
        orgResponseDTO.setOrgName(organization.getOrgName());
        orgResponseDTO.setOrgAddress(organization.getOrgAddress());
        orgResponseDTO.setOrgLocation(organization.getOrgLocation());
        orgResponseDTO.setOrgEmail(organization.getOrgEmail());
        return orgResponseDTO;
    }

    public void updateOrganizationById(String id , OrgUpdateDTO orgUpdateDTO){

        Optional<OrganizationEntity> optionalOrganizationResponse = orgRepository.findById(id);

        OrganizationEntity  organization = optionalOrganizationResponse.get();

        organization.setOrgType(orgUpdateDTO.getOrgType());
        organization.setOrgName(orgUpdateDTO.getOrgName());
        organization.setOrgAddress(orgUpdateDTO.getOrgAddress());
        organization.setOrgLocation(orgUpdateDTO.getOrgLocation());
        organization.setOrgEmail(orgUpdateDTO.getOrgEmail());

        orgRepository.save(organization);

    }

    public void deleteById(String id) {
        Optional<OrganizationEntity> optionalOrganizationResponse = orgRepository.findById(id);
        OrganizationEntity  organization = optionalOrganizationResponse.get();
        orgRepository.delete(organization);
    }


}

