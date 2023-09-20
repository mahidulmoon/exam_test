package com.example.mopa.thymeleaf.domain;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
