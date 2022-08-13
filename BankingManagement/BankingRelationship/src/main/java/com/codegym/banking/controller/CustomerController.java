package com.codegym.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @GetMapping("/list")
    public ModelAndView listCustomers(){
        ModelAndView modelAndView = new ModelAndView ("customer/listCustomers");
        return modelAndView;
    }
}
