//package com.learn.restapi.service;
//
//import com.learn.restapi.LearnRestAPIApplication;
//import com.learn.restapi.dto.WarehouseReqDTO;
//import com.learn.restapi.dto.WarehouseRespDTO;
//import com.learn.restapi.entity.Warehouse;
//import com.learn.restapi.exception.DataNotFoundException;
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
//@Slf4j
//@SpringBootTest(classes = LearnRestAPIApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//public class WarehouseServiceTest {
//
//    @Autowired
//    private WarehouseService warehouseService;
//
//    @Test
//    public void findAll() {
//        List<Warehouse> warehouseRespDTOTest = warehouseService.findAll();
//        assertThat(warehouseRespDTOTest.size()).isNotNull();
//        for (int i = 0; i < warehouseRespDTOTest.size(); i++) {
//        }
//    }
//
//    @Test
//    public void findById() {
//
//        Integer id = 1;
//        Warehouse warehouse = warehouseService.findById(id);
//
//        assertThat(warehouse)
//                .hasFieldOrProperty("idWarehouse")
//                .hasFieldOrProperty("namaBarang")
//                .hasFieldOrProperty("merkBarang")
//                .hasFieldOrProperty("hargaBarang")
//                .hasFieldOrProperty("stockBarang");
//    }
//
//    @Test
//    public void create(){
//        WarehouseReqDTO warehouseReqDTO = new WarehouseReqDTO();
//
//        String namaBarang  = "nasi";
//        String merkBarang = "goreng";
//        Integer hargaBarang = 10000;
//        Integer stockBarang = 100;
//
//        warehouseReqDTO.setNamaBarang(namaBarang);
//        warehouseReqDTO.setHargaBarang(hargaBarang);
//        warehouseReqDTO.setStockBarang(stockBarang);
//
//        WarehouseRespDTO warehouseRespDTO = warehouseService.create(warehouseReqDTO);
//        assertThat(warehouseRespDTO.getNamaBarang()).isEqualTo(namaBarang);
//        assertThat(warehouseRespDTO.getHargaBarang()).isEqualTo(hargaBarang);
//        assertThat(warehouseRespDTO.getStockBarang()).isEqualTo(stockBarang);
//    }
//
//    @Test
//    public void update(){
//
//        WarehouseReqDTO warehouseReqDTO = new WarehouseReqDTO();
//
//        Integer id = 1;
//        String namaBarang  = "nasi";
//        String merkBarang = "padang";
//        Integer hargaBarang = 15000;
//        Integer stockBarang = 80;
//
//        warehouseReqDTO.setNamaBarang(namaBarang);
//        warehouseReqDTO.setHargaBarang(hargaBarang);
//        warehouseReqDTO.setStockBarang(stockBarang);
//
//        WarehouseRespDTO warehouseRespDTO = warehouseService.update(id, warehouseReqDTO);
//        assertThat(warehouseRespDTO.getNamaBarang()).isEqualTo(namaBarang);
//        assertThat(warehouseRespDTO.getHargaBarang()).isEqualTo(hargaBarang);
//        assertThat(warehouseRespDTO.getStockBarang()).isEqualTo(stockBarang);
//    }
//
//    @Test
//    public void delete(){
//        Integer id = 2;
//        List<Warehouse> warehouses = warehouseService.findAll();
//        warehouseService.delete(id);
//
//        List<Warehouse> warehouseServiceAll = warehouseService.findAll();
//        assertThat(warehouses.size())
//                .isGreaterThan(warehouseServiceAll.size());
//    }
//
//    @Test (expected = DataNotFoundException.class)
//    public void failedFindById(){
//
//        Integer id = 100;
//        Warehouse warehouse = warehouseService.findById(id);
//        assertThat(warehouse.getIdWarehouse()).isNotEqualTo(id);
//    }
//}
