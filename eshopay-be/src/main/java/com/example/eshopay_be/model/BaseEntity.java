package com.example.eshopay_be.model;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // @Column(name = "is_delete", nullable = false)
    // private Boolean isDelete=false;
}
