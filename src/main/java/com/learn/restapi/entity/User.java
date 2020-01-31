package com.learn.restapi.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * This is Javadoc comment
 */

@Entity
@Data

public class User {

    @Id //Sebagai Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk mengenerate database
    @Column(name = "USER_id")
    private Integer idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "saldo")
    private Integer saldoUser;
}
