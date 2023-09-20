package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class UserEntity extends BaseEntity{

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private Boolean enabled = Boolean.TRUE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name ="users_roles", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
    private Set<Role> roles=new HashSet<>();

}
