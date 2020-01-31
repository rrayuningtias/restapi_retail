package com.learn.restapi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class TransactionReqDTO {

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idWarehouse;

    @NotNull
    private int qtyTransaction;

    @NotNull
    private Date date;

}
