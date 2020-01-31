package com.learn.restapi.controller;

import com.learn.restapi.dto.TransactionReqDTO;
import com.learn.restapi.service.TransactionService;
import com.learn.restapi.util.AES;
import com.learn.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping (value = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

//    @Autowired
//    AES aes;

    @PostMapping
    ResponseEntity<Response> addTransaction (@RequestBody @Validated TransactionReqDTO transactionReqDTO) throws UnsupportedEncodingException {
        Response response = new Response();
        response.setMessage("Success Create Data");
        response.setData(transactionService.create(transactionReqDTO));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    ResponseEntity<Response> findAll () throws UnsupportedEncodingException{
        Response response = new Response();
        response.setMessage("Success Find All Data");
        response.setData(transactionService.findAll());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //ngambil request data berdasarkan id
    @GetMapping(value = "/{id}")
    ResponseEntity<Response> findById (@PathVariable ("id")Integer id) throws UnsupportedEncodingException {
        Response response = new Response();
        response.setMessage("Success Find Data By ID");
        response.setData(transactionService.findById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
