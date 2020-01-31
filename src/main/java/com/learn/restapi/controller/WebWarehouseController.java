package com.learn.restapi.controller;

import com.learn.restapi.dto.WarehouseReqDTO;
import com.learn.restapi.dto.WarehouseRespDTO;
import com.learn.restapi.service.WarehouseService;
import com.learn.restapi.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(value = "/webWarehouse")
public class WebWarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    AES aes;

    @GetMapping(value = "pageWarehouse")
    public ModelAndView warehouseGet() {
        ModelAndView modelAndView = new ModelAndView("warehouse");

        modelAndView.addObject("allWarehouse", warehouseService.findAll());
        return modelAndView;
    }

    //  Untuk direct ke halaman Create Product
    @GetMapping(value = "createWarehouse")
    public ModelAndView warehouseCreate() {
        ModelAndView modelAndView = new ModelAndView("warehouse_create");

        modelAndView.addObject("allWarehouse", new WarehouseRespDTO());
        return modelAndView;
    }

    //  Untuk direct ke halaman Details Product
    @PostMapping("createWarehouse")
    public ModelAndView createWarehouse(@Valid WarehouseReqDTO warehouse, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("warehouse_create");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("namaBarang")) {
                    mav.addObject("errorProductName", error.getDefaultMessage());
                }
                if (error.getField().equals("hargaBarang")) {
                    mav.addObject("errorPrice", error.getDefaultMessage());
                }
                if (error.getField().equals("stockBarang")) {
                    mav.addObject("errorStock", error.getDefaultMessage());
                }
            }

            mav.addObject("warehouseCreate", warehouse);
            return mav;
        }

        warehouseService.create(warehouse);
        modelAndView.addObject("allWarehouse", warehouseService.findAll());
        modelAndView.setViewName("redirect:/webWarehouse/pageWarehouse");
        return modelAndView;
    }

    @GetMapping(value = "detailsWarehouse/{idWHEncrypt}")
    public ModelAndView detailsWarehouse(@PathVariable ("idWHEncrypt") String idWHEncrypt) {
        ModelAndView modelAndView = new ModelAndView();

//        warehouseService.findById(id);
        Integer id = Integer.valueOf(aes.decrypt(idWHEncrypt));
        modelAndView.addObject("allWarehouse", warehouseService.findById(id));
        modelAndView.setViewName("warehouse_details");
        return modelAndView;
    }

    @PostMapping(value = "detailsWarehouse/updateWarehouse/{idWHEncrypt}")
    public ModelAndView updateWarehouse(@PathVariable ("idWHEncrypt") String idWHEncrypt, @Valid WarehouseReqDTO warehouse, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        Integer id = Integer.valueOf(aes.decrypt(idWHEncrypt));

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("warehouse_update");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("namaBarang")) {
                    mav.addObject("errorProductName", error.getDefaultMessage());
                }
                if (error.getField().equals("hargaBarang")) {
                    mav.addObject("errorPrice", error.getDefaultMessage());
                }
                if (error.getField().equals("stockBarang")) {
                    mav.addObject("errorStock", error.getDefaultMessage());
                }
            }

            mav.addObject("updateWarehouse", warehouse);
            return mav;
        }

        modelAndView.addObject("allWarehouse", warehouseService.update(id, warehouse));
        modelAndView.setViewName("redirect:/webWarehouse/pageWarehouse");
        return modelAndView;
    }

    //  Untuk warehouse_update.html
    @GetMapping(value = "detailsWarehouse/updateWarehouse/{idWHEncrypt}")
    public ModelAndView warehouseUpdate(@PathVariable("idWHEncrypt") String idWHEncrypt) {
        ModelAndView modelAndView = new ModelAndView("warehouse_update");

        Integer id = Integer.valueOf(aes.decrypt(idWHEncrypt));
        modelAndView.addObject("allWarehouse", warehouseService.findById(id));
        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public RedirectView deleteWarehouse(@PathVariable ("id") Integer id) {

        warehouseService.delete(id);
        return new RedirectView("/retail/webWarehouse/pageWarehouse");
    }
}