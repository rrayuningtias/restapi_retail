package com.learn.restapi.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class TransactionRespDTO {

    private Integer idTransaction;
    private Integer idUser;
    private Integer idWarehouse;
    private int qtyTransaction;
    private Integer totalTransaction;
    private String namaBarang;
    private String username;
    private Integer saldoUser;

    @DateTimeFormat
    private Date date;
}
