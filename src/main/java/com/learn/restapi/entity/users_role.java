package com.learn.restapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class users_role {

    @Id
    @Column(name = "id_users", nullable = false)
    private String idUsers;

    @Column(name = "id_roles", nullable = false)
    private String idRoles;
}
