package com.learn.restapi.service;

import com.learn.restapi.dto.TransactionReqDTO;
import com.learn.restapi.dto.TransactionRespDTO;
import com.learn.restapi.dto.UserRespDTO;
import com.learn.restapi.dto.WarehouseRespDTO;
import com.learn.restapi.entity.Transaction;
import com.learn.restapi.entity.User;
import com.learn.restapi.entity.Warehouse;
import com.learn.restapi.exception.DataNotFoundException;
import com.learn.restapi.repository.TransactionRepository;
import com.learn.restapi.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    AES aes;


    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById (Integer id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (!optionalTransaction.isPresent()) {
            throw new DataNotFoundException("Data tidak ditemukan");
        }
        return optionalTransaction.get();
    }

    public TransactionRespDTO create (TransactionReqDTO transactionReqDTO) throws UnsupportedEncodingException {

        WarehouseRespDTO warehouse = warehouseService.findById(transactionReqDTO.getIdWarehouse());
        Integer getHargaBarang = warehouse.getHargaBarang();
        int getQtyTransaction = transactionReqDTO.getQtyTransaction();
        Integer totalTransaction = getHargaBarang * getQtyTransaction;

        //Update Stock Barang
        int stockBarang = warehouse.getStockBarang()-getQtyTransaction;
        if (stockBarang < 0) {
            throw new IllegalArgumentException("Pembelian melebihi ketersedian barang, transaksi tidak dapat dilakukan");
        }

        //Update Saldo
        UserRespDTO user = userService.findById(transactionReqDTO.getIdUser());
        Integer saldoAwal = user.getSaldoUser();
        Integer saldoAkhir = saldoAwal - totalTransaction;
        if (saldoAkhir < 0) {
            throw new IllegalArgumentException("Saldo tidak cukup, transaksi tidak bisa dilakukan");
        }

        WarehouseRespDTO products = warehouseService.findById(transactionReqDTO.getIdWarehouse());
        String transactionProduct = products.getNamaBarang();

        UserRespDTO users = userService.findById(transactionReqDTO.getIdUser());
        String transactionUser = users.getUsername();

        Transaction transaction = new Transaction();
        transaction.setDate(transactionReqDTO.getDate());
        warehouse.setStockBarang(stockBarang);
        transaction.setTotalTransaction(totalTransaction);
        transaction.setIdUser(transactionReqDTO.getIdUser());
        transaction.setIdWarehouse(transactionReqDTO.getIdWarehouse());
        transaction.setQtyTransaction(transactionReqDTO.getQtyTransaction());
        user.setSaldoUser(saldoAkhir);
        transaction.setSaldoUser(saldoAkhir);
        transaction.setNamaBarang(transactionProduct);
        transaction.setUsername(transactionUser);

        Warehouse warehouseEntity = new Warehouse();
        warehouseEntity.setNamaBarang(warehouse.getNamaBarang());
        warehouseEntity.setHargaBarang(warehouse.getHargaBarang());
        warehouseEntity.setStockBarang(warehouse.getStockBarang());
        warehouseEntity.setIdWarehouse(warehouse.getIdBarang());

        warehouseService.save(warehouseEntity);

        User userEntity = new User();
        userEntity.setSaldoUser(user.getSaldoUser());
        userEntity.setUsername(user.getUsername());
        userEntity.setIdUser(user.getId());

        userService.save(userEntity);

        transactionRepository.save(transaction);

        TransactionRespDTO transactionRespDTO = new TransactionRespDTO();
        transactionRespDTO.setDate(transaction.getDate());
        transactionRespDTO.setTotalTransaction(transaction.getTotalTransaction());
        transactionRespDTO.setIdUser(transaction.getIdUser());
        transactionRespDTO.setIdWarehouse(transaction.getIdWarehouse());
        transactionRespDTO.setIdTransaction(transaction.getIdTransaction());
        transactionRespDTO.setQtyTransaction(transaction.getQtyTransaction());
        transactionRespDTO.setNamaBarang(transaction.getNamaBarang());
        transactionRespDTO.setUsername(transaction.getUsername());

        transactionRespDTO.setUsername(transactionUser);
        transactionRespDTO.setNamaBarang(transactionProduct);
        transactionRespDTO.setSaldoUser(saldoAkhir);

        return transactionRespDTO;
    }
}
