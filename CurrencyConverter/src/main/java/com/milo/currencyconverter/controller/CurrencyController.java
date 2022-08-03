package com.milo.currencyconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurrencyController {
    @GetMapping("/currency")
    public String convert(){
        return "currency";
    }
    @PostMapping("/result")
    public String result(@RequestParam String usd, Model model){
        float result= Float.parseFloat(usd) * 23000;
        model.addAttribute("result",result);
        return  "result";
    }
}
