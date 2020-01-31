package com.learn.restapi.controller;

import com.learn.restapi.dto.UserReqDTO;
import com.learn.restapi.service.UserService;
import com.learn.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<Response> findAll () throws UnsupportedEncodingException {
        Response response = new Response();
        response.setMessage("Success Find All Data");
        response.setData(userService.findAll());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //ngambil request data berdasarkan id
    @GetMapping(value = "/{id}")
    ResponseEntity<Response> getById (@PathVariable ("id")Integer id) throws UnsupportedEncodingException {
        Response response = new Response();
        response.setMessage("Success Find Data By ID");
        response.setData(userService.findById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated UserReqDTO userReqDTO) {
        Response response = new Response();
        response.setMessage("Success Create Data");
        response.setData(userService.create(userReqDTO));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    //update data berdasarkan id
    @PutMapping(value = "/{id}")
    ResponseEntity<Response> update (@PathVariable ("id") Integer id, @RequestBody @Validated UserReqDTO userReqDTO) {
        Response response = new Response();
        response.setMessage("Update Success");
        response.setData(userService.update(id, userReqDTO));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    //delete data berdasarkan id
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable ("id") Integer id) throws UnsupportedEncodingException {
        Response response = new Response();
        response.setMessage("Data Deleted");
        response.setData(userService.findById(id));
        userService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
