package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.Utils.AuthLoggedUser;
import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.dto.request.UserEntityRequestDTO;
import com.example.mopa.thymeleaf.dto.response.UserEntityResponseDTO;
import com.example.mopa.thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthLoggedUser authLoggedUser;


    public UserEntityRequestDTO createRegistration(UserEntityRequestDTO registrationDTO) {

        UserEntity registrationEntity = new UserEntity();
        registrationEntity.setEmail(registrationDTO.getEmail());
        registrationEntity.setPhoneNumber(registrationDTO.getPhoneNumber());
        registrationEntity.setUsername(registrationDTO.getUsername());
        registrationEntity.setRoles(registrationDTO.getRoles());
        registrationEntity.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(registrationEntity);
        return registrationDTO;
    }


    public Object createLogin(UserEntityRequestDTO registrationDTO) {

        Optional<UserEntity> obj = userRepository.findAllByEmail(registrationDTO.getEmail());

        String emailFound = obj.get().getEmail();
        String passwordFound = obj.get().getPassword();

        if (emailFound != registrationDTO.getEmail()) {
            return new RuntimeException("Not Found");
        }


        return registrationDTO;
    }


    public List<UserEntityResponseDTO> getAllUserList() {

        List<UserEntityResponseDTO> userList = new ArrayList<>();

        List<UserEntity> entityList = new ArrayList<>();
        entityList = userRepository.findAllByUsername(authLoggedUser.getLoggedAuthUser());

        if (entityList.get(0).getRoles().iterator().next().getName().toString().equals("ADMIN")){
            entityList = new ArrayList<>();
            entityList = userRepository.findAll();
        }


        entityList.forEach(response -> {
            UserEntityResponseDTO objectResponse = new UserEntityResponseDTO();
            objectResponse.setId(response.getId());
            objectResponse.setEmail(response.getEmail());
            objectResponse.setUsername(response.getUsername());
            objectResponse.setRoles(response.getRoles());
            objectResponse.setPhoneNumber(response.getPhoneNumber());
            objectResponse.setUpdatedAt(response.getUpdatedAt());
            objectResponse.setCreatedAt(response.getCreatedAt());

            userList.add(objectResponse);
        });

        return userList;
    }

    public void updateUserByUserId(String id, UserEntityRequestDTO requestDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            throw new RuntimeException("Not found User Id");
        }

        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setUsername(requestDTO.getUsername());
        userEntity.setEmail(requestDTO.getEmail());
        userEntity.setPhoneNumber(requestDTO.getPhoneNumber());
        userEntity.setRoles(requestDTO.getRoles());
        userRepository.save(userEntity);
    }

    public UserEntityResponseDTO getAllUserByBuserId(String id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            throw new RuntimeException("Not found User Id");
        }

        UserEntity userEntity = optionalUserEntity.get();
        UserEntityResponseDTO userEntityResponseDTO = new UserEntityResponseDTO();
        userEntityResponseDTO.setId(userEntity.getId());
        userEntityResponseDTO.setUsername(userEntity.getUsername());
        userEntityResponseDTO.setEmail(userEntity.getEmail());
        userEntityResponseDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userEntityResponseDTO.setRoles(userEntity.getRoles());

        return userEntityResponseDTO;
    }
}

