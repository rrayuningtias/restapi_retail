package com.learn.restapi.controller;

import com.learn.restapi.LearnRestAPIApplication;
import com.learn.restapi.dto.TransactionReqDTO;
import com.learn.restapi.dto.TransactionRespDTO;
import com.learn.restapi.dto.UserReqDTO;
import com.learn.restapi.exception.DataNotFoundException;
import com.learn.restapi.service.TransactionService;
import com.learn.restapi.service.UserService;
import com.learn.restapi.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping(value = "/webTransaction")
public class WebTransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    @GetMapping(value = "pageTransaction")
    public ModelAndView transactionGet() {
        ModelAndView modelAndView = new ModelAndView("transaction");

        modelAndView.addObject("allTransaction", transactionService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/checkout")
    public ModelAndView createTransaction() throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("transaction_checkout");
        TransactionRespDTO transactionRespDTO = new TransactionRespDTO();

        userService.findAll();
        modelAndView.addObject("allUser", userService.findAll());
        modelAndView.addObject("allWarehouse", warehouseService.findAll());
        return modelAndView;
    }


    @PostMapping(value = "/checkout")
    public ModelAndView transactionCreate(@Valid TransactionReqDTO transactionReqDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("transaction_checkout");

        try {
            transactionService.create(transactionReqDTO);
            modelAndView.addObject("allTransaction", transactionService.findAll());
            modelAndView.setViewName("redirect:/webTransaction/pageTransaction");

        } catch (Exception e) {
            log.info("masuk catch " + e.getMessage());
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("transaction", new TransactionReqDTO());
            modelAndView.setViewName("transaction_checkout");
        }

        return modelAndView;
    }

    @GetMapping(value = "detailsTransaction/{id}")
    public ModelAndView transactionDetails(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("transaction_details");

        modelAndView.addObject("allTransaction", transactionService.findById(id));
        return modelAndView;
    }
}
