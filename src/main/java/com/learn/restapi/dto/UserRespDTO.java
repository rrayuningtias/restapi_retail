package com.learn.restapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRespDTO {

    private Integer id;
    private String username;
    private Integer saldoUser;
    private String idEncrypt;
}
