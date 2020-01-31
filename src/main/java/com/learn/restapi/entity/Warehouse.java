package com.learn.restapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity

public class Warehouse {

    @Id /*Sebagai Id*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAREHOUSE_id")
    private Integer idWarehouse;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "harga_barang")
    private Integer hargaBarang;

    @Column(name = "stock_barang")
    @PositiveOrZero
    private int stockBarang;


}
