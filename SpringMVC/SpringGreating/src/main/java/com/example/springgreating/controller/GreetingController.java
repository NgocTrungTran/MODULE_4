package com.example.springgreating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam String message) {
        return message;
    }
}

