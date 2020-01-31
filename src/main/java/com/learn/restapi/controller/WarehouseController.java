package com.learn.restapi.controller;

import com.learn.restapi.dto.WarehouseReqDTO;
import com.learn.restapi.service.WarehouseService;
import com.learn.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    ResponseEntity<Response> findAll() {
        Response response = new Response();
        response.setMessage("Success Find All Data");
        response.setData(warehouseService.findAll());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    //ngambil req data berdasarkan id
    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Success Find Data By ID");
        response.setData(warehouseService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated WarehouseReqDTO warehouseReqDTO) {
        Response response = new Response();
        response.setMessage("Success Create Data");
        response.setData(warehouseService.create(warehouseReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id") Integer id, @RequestBody @Validated WarehouseReqDTO warehouseReqDTO) {
        Response response = new Response();
        response.setMessage("Update Success");
        response.setData(warehouseService.update(id, warehouseReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id") Integer id) {
        Response response = new Response();
        response.setMessage("Data Deleted");
        response.setData(warehouseService.findById(id));
        warehouseService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
