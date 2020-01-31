package com.learn.restapi.repository;

import com.learn.restapi.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findByIdWarehouse(Integer id);
}
