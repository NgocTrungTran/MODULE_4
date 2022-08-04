package com.example.sandwichcondiment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CondimentController {
    @GetMapping(value = "/")
    public String home() {
        return "condiment";
    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("condiment") String[] condiment, ModelMap model) {
        if ( condiment.length == 0) {
            model.addAttribute ( "condiment", "Please choose at least one condiment" );
        }else {
            model.addAttribute ( "condiment", condiment );
        }
        return "condiment";
    }
}
