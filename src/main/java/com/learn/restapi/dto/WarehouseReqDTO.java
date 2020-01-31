package com.learn.restapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class WarehouseReqDTO {

    @NotEmpty (message = "Nama produk wajib diisi.")
    private String namaBarang;

    @NotNull (message = "Harga produk wajib diisi.")
    @PositiveOrZero
    private Integer hargaBarang;

    @NotNull (message = "Stock produk wajib diisi.")
    @PositiveOrZero
    private Integer stockBarang;
}
