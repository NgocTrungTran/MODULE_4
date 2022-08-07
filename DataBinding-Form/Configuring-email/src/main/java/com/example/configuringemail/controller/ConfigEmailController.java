package com.example.configuringemail.controller;

import com.example.configuringemail.model.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigEmailController {
    @GetMapping("/config")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView ( "config", "config", new Config () );
        modelAndView.addObject ( "languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"} );
        modelAndView.addObject ( "pageSize", new Integer[]{5, 10, 25, 50, 100} );
        return modelAndView;
    }

    @PostMapping("/settings-info")
    public ModelAndView login(@ModelAttribute("config") Config config) {
        if (config.getSpamsFilter () ==  null ) {
            config.setSpamsFilter ( "Do not enable" );
        }
        ModelAndView modelAndView = new ModelAndView ( "settingsinfo" );
        modelAndView.addObject ( "config", config );
        return modelAndView;
    }
}
