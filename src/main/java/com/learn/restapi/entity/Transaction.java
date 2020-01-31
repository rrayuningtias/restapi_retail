package com.learn.restapi.entity;

import lombok.Data;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Date;

/**
 * This is a Javadoc comment
 */
@Data
@Entity

public class Transaction {

    @Id //Sebagai Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "TRANSACTION_id")
    private Integer idTransaction;

    @Column(name = "USER_id")
    private Integer idUser;

    @Column(name = "WAREHOUSE_id")
    private Integer idWarehouse;

    @Column(name = "qty_Transaction")
    private int qtyTransaction;

    @Column(name = "total_Transaction")
    private Integer totalTransaction;

    @Column(name = "tanggal")
    private Date date;

    @Column(name = "username")
    private String username;

    @Column(name = "nama_barang")
    private String namaBarang;

    @Column(name = "saldo")
    private Integer saldoUser;

}
