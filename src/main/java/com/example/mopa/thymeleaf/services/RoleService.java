package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.Role;
import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.response.RoleResponse;
import com.example.mopa.thymeleaf.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final NamedParameterJdbcTemplate coreDB; ;

    public RoleRequest createRole(RoleRequest roleRequest) {

        boolean existsByName = roleRepository.existsByName(roleRequest.getName());
        if (existsByName){
            throw new RuntimeException("Already Found Found");
        }

        Role entity = new Role();
        entity.setName(roleRequest.getName());
        roleRepository.saveAndFlush(entity);

        return roleRequest;
    }

    public List<RoleResponse> getAllRoleListList() {
/*
        StringBuilder sql = new StringBuilder();
        Map<String, Object> param = new HashMap<>();
        sql.append(" SELECT * FROM role re WHERE re.name=:name ");
        param.put("name","ADMIN");
        List<Role> orderStatusList = coreDB.query(sql.toString(), param, new BeanPropertyRowMapper(Role.class));
*/

        List<Role> roleList = roleRepository.findAllByOrderByIdDesc();

        List<RoleResponse> roleResponseList = new ArrayList<>();

        List<RoleResponse> gg = roleResponseList;

        roleList.forEach(response -> {

            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(response.getId());
            roleResponse.setName(response.getName());
            roleResponse.setCreatedAt(response.getCreatedAt());
            roleResponse.setUpdatedAt(response.getUpdatedAt());

            roleResponseList.add(roleResponse);
        });

        gg = roleResponseList.stream().sorted((o1, o2) -> o1.getName().
                        compareTo(o2.getName())).
                collect(Collectors.toList());
        return gg;
    }

    public RoleResponse getAllRoleById(String id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent()) {
            throw new RuntimeException("Not Found");
        }
        Role role = roleOptional.get();
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }

    public void updateRoleById(String id, RoleRequest roleRequest) {

        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent()) {
            throw new RuntimeException("Not Found");
        }
        Role role = roleOptional.get();
        role.setName(roleRequest.getName());
        roleRepository.save(role);
    }

    public void deleteById(String id) {
        Optional<Role> optional = roleRepository.findById(id);
        Role role = optional.get();
        roleRepository.delete(role);
    }
}

