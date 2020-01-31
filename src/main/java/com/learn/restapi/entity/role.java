package com.learn.restapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class role {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}
