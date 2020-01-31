//package com.learn.restapi.service;
//
//import com.learn.restapi.LearnRestAPIApplication;
//import com.learn.restapi.dto.TransactionReqDTO;
//import com.learn.restapi.dto.TransactionRespDTO;
//import com.learn.restapi.entity.Transaction;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = LearnRestAPIApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@Slf4j
//public class TransactionServiceTest {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @Test
//    public void findAll() {
//        List<Transaction> transactionRespDtoTest = transactionService.findAll();
//        assertThat(transactionRespDtoTest.size()).isNotNull();
//        for (int i = 0; i < transactionRespDtoTest.size(); i++) {
//        }
//    }
//
//    @Test
//    public void findbyId() {
//        Integer id = 1;
//        Transaction transaction = transactionService.findById(id);
//
//        assertThat(transaction)
//                .hasFieldOrProperty("idUser")
//                .hasFieldOrProperty("idWarehouse")
//                .hasFieldOrProperty("qtyTransaction")
//                .hasFieldOrProperty("totalTransaction");
//    }
//
//    @Test
//    public void create() {
//        TransactionReqDTO transactionReqDTO = new TransactionReqDTO();
//
//        Integer idUser = 1;
//        Integer idWarehouse = 1;
//        int qtyTransaction = 5;
//
//        transactionReqDTO.setIdUser(idUser);
//        transactionReqDTO.setIdWarehouse(idWarehouse);
//        transactionReqDTO.setQtyTransaction(qtyTransaction);
//
//        TransactionRespDTO transactionRespDTO = transactionService.create(transactionReqDTO);
//        assertThat(transactionRespDTO.getIdUser()).isEqualTo(idUser);
//        assertThat(transactionRespDTO.getIdWarehouse()).isEqualTo(idWarehouse);
//        assertThat(transactionRespDTO.getQtyTransaction()).isEqualTo(qtyTransaction);
//    }
//
//
//}
