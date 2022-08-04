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
        if (word.toLowerCase ().trim ().equals ( "hello" )){
            result = "Xin chao";
        }
        if (word.toLowerCase ().trim ().equals ( "yes" )){
            result = "Co";
        }
        if (word.toLowerCase ().trim ().equals ( "no" )){
            result = "Khong";
        }
        if (word.toLowerCase ().trim ().equals ( "bye" )){
            result = "Tam biet";
        }
        model.addAttribute("result",result);
        return  "result";
    }

}
