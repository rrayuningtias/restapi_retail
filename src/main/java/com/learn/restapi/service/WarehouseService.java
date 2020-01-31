package com.learn.restapi.service;

import com.learn.restapi.dto.WarehouseReqDTO;
import com.learn.restapi.dto.WarehouseRespDTO;
import com.learn.restapi.entity.Warehouse;
import com.learn.restapi.exception.DataNotFoundException;
import com.learn.restapi.repository.WarehouseRepository;
import com.learn.restapi.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    AES aes;

    public List<WarehouseRespDTO> findAll() {

        List<Warehouse> listWarehouse = warehouseRepository.findAll();
        List<WarehouseRespDTO> listWarehouseDTO = new ArrayList<>();
        for (Warehouse warehouse : listWarehouse) {
            WarehouseRespDTO warehouseRespDTO = new WarehouseRespDTO();
            warehouseRespDTO.setIdWHEncrypt(aes.encrypt(warehouse.getIdWarehouse().toString()));
            warehouseRespDTO.setIdBarang(warehouse.getIdWarehouse());
            warehouseRespDTO.setNamaBarang(warehouse.getNamaBarang());
            warehouseRespDTO.setHargaBarang(warehouse.getHargaBarang());
            warehouseRespDTO.setStockBarang(warehouse.getStockBarang());

            listWarehouseDTO.add(warehouseRespDTO);
        }

        return listWarehouseDTO;
    }

    public WarehouseRespDTO findById(Integer id) {

        Warehouse warehouse = warehouseRepository.findByIdWarehouse(id);
        WarehouseRespDTO warehouseRespDTO = new WarehouseRespDTO();
        warehouseRespDTO.setIdWHEncrypt(aes.encrypt(warehouse.getIdWarehouse().toString()));
        warehouseRespDTO.setIdBarang(warehouse.getIdWarehouse());
        warehouseRespDTO.setNamaBarang(warehouse.getNamaBarang());
        warehouseRespDTO.setHargaBarang(warehouse.getHargaBarang());
        warehouseRespDTO.setStockBarang(warehouse.getStockBarang());

        return warehouseRespDTO;
    }

    public WarehouseRespDTO create (WarehouseReqDTO warehouseReqDTO) {
        Warehouse warehouse= new Warehouse();
        warehouse.setNamaBarang(warehouseReqDTO.getNamaBarang());
        warehouse.setHargaBarang(warehouseReqDTO.getHargaBarang());
        warehouse.setStockBarang(warehouseReqDTO.getStockBarang());

        warehouseRepository.save(warehouse);

        WarehouseRespDTO warehouseRespDTO = new WarehouseRespDTO();
        warehouseRespDTO.setIdBarang(warehouse.getIdWarehouse());
        warehouseRespDTO.setNamaBarang(warehouse.getNamaBarang());
        warehouseRespDTO.setHargaBarang(warehouse.getHargaBarang());
        warehouseRespDTO.setStockBarang(warehouse.getStockBarang());

        return warehouseRespDTO;
    }

    public WarehouseRespDTO update (Integer id, WarehouseReqDTO warehouseReqDTO) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) throw new DataNotFoundException("Data tidak ditemukan");

        Warehouse warehouse = new Warehouse();
        warehouse.setIdWarehouse(id);
        warehouse.setNamaBarang(warehouseReqDTO.getNamaBarang());
        warehouse.setHargaBarang(warehouseReqDTO.getHargaBarang());
        warehouse.setStockBarang(warehouseReqDTO.getStockBarang());

        WarehouseRespDTO warehouseRespDTO = new WarehouseRespDTO();
        warehouseRespDTO.setIdBarang(id);
        warehouseRespDTO.setNamaBarang(warehouse.getNamaBarang());
        warehouseRespDTO.setHargaBarang(warehouse.getHargaBarang());
        warehouseRespDTO.setStockBarang(warehouse.getStockBarang());

        warehouseRepository.save(warehouse);
        return warehouseRespDTO;
    }

    public Warehouse delete (Integer id) {
        warehouseRepository.deleteById(id);
        return null;
    }

    public void save (Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

}
