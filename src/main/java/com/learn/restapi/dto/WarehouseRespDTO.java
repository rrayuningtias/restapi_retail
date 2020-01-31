package com.learn.restapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WarehouseRespDTO {
    private Integer idBarang;
    private String namaBarang;
    private Integer hargaBarang;
    private Integer stockBarang;
    private String idWHEncrypt;
}
