package com.example.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {
    @GetMapping("/dictionary")
    public String convert(){
        return "dictionary";
    }
    @PostMapping("/result")
    public String result(@RequestParam String word, Model model){
        String result = null;
        switch (word.toLowerCase ().trim ()) {
            case "hello":
                result = "Xin chao";
                break;
            case "yes":
                result = "Co";
                break;
            case "no":
                result = "Khong";
                break;
            case "bye":
                result = "Tam biet";
                break;
            default:
                result = "Khong tim thay";
                break;
        }
        model.addAttribute("result",result);
        return  "result";
    }

}
