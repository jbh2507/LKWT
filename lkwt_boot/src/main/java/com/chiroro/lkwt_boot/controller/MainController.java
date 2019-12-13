package com.chiroro.lkwt_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MainController
 */
@Controller
@RequestMapping("/tnm/*")
public class MainController {

    @GetMapping("/main")
    public void tnm(Model model){
        System.out.println("\t TNM");
        model.addAttribute("userName", "tester"+(char)('A'+(int)(Math.random()*21)));
    }

    
}