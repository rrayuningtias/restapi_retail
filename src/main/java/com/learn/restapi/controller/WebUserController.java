package com.learn.restapi.controller;

import com.learn.restapi.dto.UserReqDTO;
import com.learn.restapi.service.UserService;
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

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping(value = "/webUser")
public class WebUserController {

    @Autowired
    UserService userService;

    @Autowired
    AES aes;

    @GetMapping(value = "pageUser")
    public ModelAndView userGet() throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("user");

        modelAndView.addObject("allUser", userService.findAll());
        return modelAndView;
    }

    //  Untuk direct ke halaman Create User
    @GetMapping(value = "createUser")
    public ModelAndView createUser() {
        ModelAndView modelAndView = new ModelAndView("user_create");

        return modelAndView;
    }

    //  Untuk user_create.html
    @PostMapping(value = "createUser")
    public ModelAndView createUser(@Valid UserReqDTO user, BindingResult result) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("user_create");

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("user_create");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("username")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }
                if (error.getField().equals("saldoUser")) {
                    mav.addObject("errorSaldo", error.getDefaultMessage());
                }
            }

            mav.addObject("createUser", user);
            return mav;
        }

        userService.create(user);
        modelAndView.addObject("allUser", userService.findAll());
        modelAndView.setViewName("redirect:/webUser/pageUser");
        return modelAndView;
    }

    @GetMapping(value = "detailsUser/{idEncrypt}")
    public ModelAndView detailsUser(@PathVariable ("idEncrypt") String idEncrypt) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();

        Integer id = Integer.valueOf(aes.decrypt(idEncrypt));
        modelAndView.addObject("allUser", userService.findById(id));
        modelAndView.setViewName("user_details");
        return modelAndView;
    }

    //  Untuk user_update.html
    @PostMapping(value = "detailsUser/updateUser/{idEncrypt}")
    public ModelAndView updateUser(@PathVariable("idEncrypt") String idEncrypt, @Valid UserReqDTO user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        Integer id = Integer.valueOf(aes.decrypt(idEncrypt));

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("user_update");

            for (FieldError error : result.getFieldErrors()) {
                mav.addObject("result", error.getDefaultMessage());

                if (error.getField().equals("username")) {
                    mav.addObject("errorName", error.getDefaultMessage());
                }
                if (error.getField().equals("saldoUser")) {
                    mav.addObject("errorSaldo", error.getDefaultMessage());
                }
            }

            mav.addObject("updateUser", user);
            return mav;
        }

        modelAndView.addObject("allUser", userService.update(id, user));
        modelAndView.setViewName("redirect:/webUser/pageUser");
        return modelAndView;
    }

    //  Untuk direct ke halaman Details User
    @GetMapping(value = "detailsUser/updateUser/{idEncrypt}")
    public ModelAndView userUpdate(@PathVariable("idEncrypt") String idEncrypt) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView("user_update");

        Integer id = Integer.valueOf(aes.decrypt(idEncrypt));
        modelAndView.addObject("allUser", userService.findById(id));
        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public RedirectView delete(@PathVariable ("id") Integer id) {

        userService.delete(id);
        return new RedirectView("/retail/webUser/pageUser");
    }
}